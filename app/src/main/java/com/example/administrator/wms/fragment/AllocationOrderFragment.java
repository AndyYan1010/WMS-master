package com.example.administrator.wms.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wms.R;
import com.example.administrator.wms.adapter.TransferAdapter;
import com.example.administrator.wms.util.Consts;
import com.example.administrator.wms.util.ProgressDialogUtil;
import com.example.administrator.wms.util.SoapUtil;
import com.example.administrator.wms.util.ToastUtils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @创建者 AndyYan
 * @创建时间 2018/6/19 10:04
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */

public class AllocationOrderFragment extends Fragment {
    private View         mRootView;
    private RecyclerView rec_view;
    private String       mOrderID;//调拨单ID

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_all_order, null);
        initView();
        initData();
        return mRootView;
    }

    private void initView() {
        rec_view = mRootView.findViewById(R.id.rec_view);

    }

    private void initData() {
        final List mData = new ArrayList();
        mData.add("物料代码");
        mData.add("名称");
        mData.add("规格");
        mData.add("数量");
        mData.add("单位");
        mData.add("调入仓库");
        mData.add("调出仓库");
        mData.add("10.1002.220220.2002");
        for (int i = 0; i < 500; i++) {
            mData.add("lol" + (i) * 10);
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 8);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (position % 7 == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        rec_view.setLayoutManager(gridLayoutManager);
        TransferAdapter adapter = new TransferAdapter(getContext(), mData);
        rec_view.setAdapter(adapter);

        String detOrdUrl = "select c.fnumber,c.fname,c.fmodel,f.fname,b.fqty,d.fname fin,e.fname fout from icstockbill a inner join icstockbillentry b on a.finterid=b.finterid" +
                "left join t_icitem c on c.fitemid=b.fitemid left join t_stock d on d.fitemid=b.fdcstockid" +
                "left join t_stock e on e.fitemid=b.fscstockid left join t_measureunit f on f.fitemid=b.funitid where ftrantype=41 and isnull(fcheckerid,0)=0 and a.fbillno='" + mOrderID + "'";
        //查询单个调拨单详情
        new ItemTask(detOrdUrl).execute();
    }

    public void setOrderID(String oderId) {
        this.mOrderID = oderId;
    }

    /*
* select c.fnumber,c.fname,c.fmodel,f.fname,b.fqty,d.fname fin,e.fname fout from icstockbill a inner join icstockbillentry b on a.finterid=b.finterid
left join t_icitem c on c.fitemid=b.fitemid left join t_stock d on d.fitemid=b.fdcstockid
left join t_stock e on e.fitemid=b.fscstockid left join t_measureunit f on f.fitemid=b.funitid where ftrantype=41 and isnull(fcheckerid,0)=0 and a.fbillno='单号'
* */
    class ItemTask extends AsyncTask<Void, String, String> {
        String sql;

        ItemTask(String sql) {
            this.sql = sql;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... voids) {
            Map<String, String> map = new HashMap<>();
            map.put("FSql", sql);
            map.put("FTable", "t_icitem");
            return SoapUtil.requestWebService(Consts.JA_select, map);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                Document doc = DocumentHelper.parseText(s);
                Element ele = doc.getRootElement();
                Iterator iter = ele.elementIterator("Cust");
                HashMap<String, String> map = new HashMap<>();
                while (iter.hasNext()) {
                    Element recordEle = (Element) iter.next();
                    map.put("fnumber", recordEle.elementTextTrim("fnumber"));//物料内码(提交订单用)
                    map.put("fname", recordEle.elementTextTrim("fname"));//
                    map.put("fmodel", recordEle.elementTextTrim("fmodel"));//
                    map.put("fname", recordEle.elementTextTrim("fname"));//
                    map.put("fqty", recordEle.elementTextTrim("fqty"));//
                    map.put("fname", recordEle.elementTextTrim("fname"));//
                    map.put("fname", recordEle.elementTextTrim("fname"));//
                }
                //填充数据到页面

            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showToast(getContext(), "未查到此商品");
                getActivity().finish();
            }
            ProgressDialogUtil.hideDialog();
        }
    }
}
