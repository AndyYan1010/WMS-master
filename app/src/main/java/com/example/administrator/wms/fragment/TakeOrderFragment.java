package com.example.administrator.wms.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.wms.R;
import com.example.administrator.wms.activity.AllocationDetailActivity;
import com.example.administrator.wms.adapter.TransferAdapter;
import com.example.administrator.wms.messageInfo.GoodsInfo;
import com.example.administrator.wms.util.Consts;
import com.example.administrator.wms.util.ProgressDialogUtil;
import com.example.administrator.wms.util.SoapUtil;
import com.example.administrator.wms.util.ToastUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/19 10:06
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class TakeOrderFragment extends Fragment implements View.OnClickListener {
    private View         mRootView;
    private RecyclerView rec_view;
    private Button       bt_scan;
    private Button       bt_submit;//提交
    private int REQUEST_CODE = 1002;//接收扫描结果
    private List            mData;//显示存储的数据
    private List<GoodsInfo> mGoodsInfo;//存储的数据对象
    private TransferAdapter adapter;
    private EditText        edit_num;
    private ImageView       img_delete;//清空物料代码

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_take_order, null);
        initView();
        initData();
        initListener();
        return mRootView;
    }

    private void initView() {
        edit_num = mRootView.findViewById(R.id.edit_num);
        img_delete = mRootView.findViewById(R.id.img_delete);
        rec_view = mRootView.findViewById(R.id.rec_view);
        bt_scan = mRootView.findViewById(R.id.bt_scan);
        bt_submit = mRootView.findViewById(R.id.bt_submit);
    }

    private void initData() {
        mData = new ArrayList();
        mGoodsInfo = new ArrayList();
        mData.add("物料代码");
        mData.add("名称");
        mData.add("规格");
        mData.add("数量");
        mData.add("单位");
        mData.add("调入仓库");
        mData.add("调出仓库");
        //        mData.add("10.1002.220220");
        //        for (int i = 0; i < 50; i++) {
        //            mData.add("lol" + (i) * 10);
        //        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 9);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (position % 7 == 0 || position % 7 == 2) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        rec_view.setLayoutManager(gridLayoutManager);
        adapter = new TransferAdapter(getContext(), mData);
        rec_view.setAdapter(adapter);
    }

    private void initListener() {
        edit_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String num = String.valueOf(edit_num.getText()).trim();
                if (!"".equals(num) && !"物料代码".equals(num) && num.length() > 5) {
                    //对比调拨单中的物料代码
                    getGoodsInfo(num);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        img_delete.setOnClickListener(this);
        bt_scan.setOnClickListener(this);
        bt_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_delete:
                edit_num.setText("");
                break;
            case R.id.bt_scan:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.bt_submit:
                AllocationDetailActivity activity = (AllocationDetailActivity) getActivity();
                String orderID = activity.getOrderID();
                //提交调拨的数据
                String detOrdUrl = "select c.fitemid,a.finterid,c.fnumber,c.fname,c.fmodel,f.fname,b.fqty,d.fname fin,e.fname fout from icstockbill a inner join icstockbillentry b on a.finterid=b.finterid " +
                        "left join t_icitem c on c.fitemid=b.fitemid left join t_stock d on d.fitemid=b.fdcstockid " +
                        "left join t_stock e on e.fitemid=b.fscstockid left join t_measureunit f on f.fitemid=b.funitid where ftrantype=41 and isnull(fcheckerid,0)=0 and isnull(FEntrySelfD0152,0)=0 and a.fbillno='" + orderID + "' and c.fnumber='" + "num" + "'";
                ProgressDialogUtil.startShow(getContext(), "正在提交");
                SubmitTask submitTask = new SubmitTask(mGoodsInfo);
                submitTask.execute();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //获取调拨单id信息，弹出dailog展示，在新的页面确定后添加到表格中
                    getGoodsInfo(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /**
     * String detOrdUrl = "select c.fitemid,a.finterid,c.fnumber,c.fname,c.fmodel,f.fname,b.fqty,d.fname fin,e.fname fout from icstockbill a inner join icstockbillentry b on a.finterid=b.finterid " +
     * "left join t_icitem c on c.fitemid=b.fitemid left join t_stock d on d.fitemid=b.fdcstockid " +
     * "left join t_stock e on e.fitemid=b.fscstockid left join t_measureunit f on f.fitemid=b.funitid where ftrantype=41 and isnull(fcheckerid,0)=0 and isnull(FEntrySelfD0152,0)=0 and a.fbillno='" + mOrderID + "' and c.fnumber='物料代码'" ;
     */

    private void getGoodsInfo(String result) {//result是一维码，也即物品的物料代码
        AllocationDetailActivity activity = (AllocationDetailActivity) getActivity();
        List<GoodsInfo> infoList = activity.getInfoList();
        if (null == result) {
            ToastUtils.showToast(getContext(), "扫描失败，请重新扫描");
            return;
        }
        isFind = false;
        ToastUtils.showToast(getContext(), result);
        for (GoodsInfo info : infoList) {
            String fnumber = info.getFnumber();//物料代码
            if (fnumber.equals(result)) {
                isFind = true;
                String finterid = info.getFinterid();
                String fitemid = info.getFitemid();
                String fname = info.getFname();//名称
                String fmodel = info.getFmodel();//规格
                String fqty = info.getFqty();//数量
                String fname1 = info.getFname1();//单位
                String fin = info.getFin();//调用仓库
                String fout = info.getFout();//调出仓库
                //弹出dialog修改数量
                showChangeView(fnumber, fname, fmodel, fqty, fname1, fin, fout, finterid, fitemid);
            }
        }
        if (!isFind) {
            ToastUtils.showToast(getContext(), "该扫描的物品不在调拨单内");
        }
    }

    private boolean isFind = false;
    private AlertDialog mAlertDialog;

    private void showChangeView(final String fnumber, final String fname, final String fmodel, String fqty, final String fname1, final String fin, final String fout, final String finterid, final String fitemid) {
        //弹一个dailog提示
        View view = View.inflate(getContext(), R.layout.dialog_change_num, null);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_spec = view.findViewById(R.id.tv_spec);
        final EditText edit_num = view.findViewById(R.id.edit_num);
        TextView tv_unit = view.findViewById(R.id.tv_unit);
        TextView tv_fin = view.findViewById(R.id.tv_fin);
        TextView tv_fout = view.findViewById(R.id.tv_fout);
        Button bt_sure = view.findViewById(R.id.bt_sure);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        mAlertDialog = builder.setView(view).create();
        mAlertDialog.show();
        tv_name.setText(fname);
        tv_spec.setText(fmodel);
        tv_unit.setText(fname1);
        tv_fin.setText(fin);
        tv_fout.setText(fout);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //把参数加入到数据表内
                String num = String.valueOf(edit_num.getText()).trim();
                if (null == num || "".equals(num) || "请输入数量".equals(num)) {
                    ToastUtils.showToast(getContext(), "数量不能为空");
                    return;
                }
                mData.add(fnumber);//物料代码
                mData.add(fname);//名称
                mData.add(fmodel);//规格
                mData.add(num);//数量
                mData.add(fname1);//单位
                mData.add(fin);//调用仓库
                mData.add(fout);//调出仓库
                //封装到对象中，方便之后提交
                GoodsInfo goodsInfo = new GoodsInfo();
                goodsInfo.setFqty(num);
                goodsInfo.setFitemid(fitemid);
                goodsInfo.setFinterid(finterid);
                mGoodsInfo.add(goodsInfo);
                mAlertDialog.hide();
                adapter.notifyDataSetChanged();
            }
        });
    }

    class SubmitTask extends AsyncTask<Void, String, String> {
        List<GoodsInfo> mData;

        SubmitTask(List mData) {
            this.mData = mData;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                //表头
                Document document = DocumentHelper.createDocument();
                Element rootElement = document.addElement("NewDataSet");
                Element cust = rootElement.addElement("Cust");
                //                cust.addElement("FHeadSelfS0166").setText(order.getMembermobile());
                //                //积分
                //                cust.addElement("FHeadSelfS01100").setText(order.getPoint());
                //                //业务类型
                //                cust.addElement("FHeadSelfS0167").setText(order.getBusinesstype());
                //                //送货地址
                //                cust.addElement("FDeliveryAddress").setText(order.getAddress());

                //表体
                Document document2 = DocumentHelper.createDocument();
                Element rootElement2 = document2.addElement("NewDataSet");
                for (GoodsInfo info : mData) {
                    Element cust2 = rootElement2.addElement("Cust");
                    //物料代码
                    cust2.addElement("FItemID").setText(info.getFitemid());
                    //数量
                    cust2.addElement("fauxqty").setText(info.getFqty());
                    //子表innerid
                    cust2.addElement("FInterID").setText(String.valueOf(info.getFinterid()));
                }
                OutputFormat outputFormat = OutputFormat.createPrettyPrint();
                outputFormat.setSuppressDeclaration(false);
                outputFormat.setNewlines(false);
                StringWriter stringWriter = new StringWriter();
                StringWriter stringWriter2 = new StringWriter();
                // xmlWriter是用来把XML文档写入字符串的(工具)
                XMLWriter xmlWriter = new XMLWriter(stringWriter, outputFormat);
                XMLWriter xmlWriter2 = new XMLWriter(stringWriter2, outputFormat);
                // 把创建好的XML文档写入字符串
                xmlWriter.write(document);
                xmlWriter2.write(document2);
                String fbtouxml = stringWriter.toString().substring(38);
                String fbtixml = stringWriter2.toString().substring(38);
                Map<String, String> map = new HashMap<>();
                map.put("FCheckerID", "1");
                //                map.put("FBtiXML", "a");
                map.put("FBtouXMl", fbtouxml);
                map.put("FBtiXML", fbtixml);
                String s = SoapUtil.requestWebService(Consts.CHECK, map);
                return SoapUtil.requestWebService(Consts.CHECK, map);
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast(getContext(), "创建参数据出错");
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ProgressDialogUtil.hideDialog();
            if ("1".equals(s)) {
                ToastUtils.showToast(getContext(), "提交成功");
                getActivity().finish();
            } else {
                ToastUtils.showToast(getContext(), "提交失败");
            }
        }
    }
}
