package com.steven.babyiyo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.steven.babyiyo.ActivityGoods;
import com.steven.babyiyo.R;
import com.steven.babyiyo.adapter.MyHeaderPageAdapter;
import com.steven.babyiyo.adapter.PageoneRecomAdapter;
import com.steven.babyiyo.bean.Pageonelv;
import com.steven.babyiyo.bean.Pageonere;
import com.steven.babyiyo.bean.Pageonevp;
import com.steven.babyiyo.component.CustomMyListView;
import com.steven.babyiyo.component.DepthPageTransformer;
import com.steven.babyiyo.component.ViewPagerScroller;
import com.steven.babyiyo.utlis.Tools;
import com.steven.babyiyo.utlis.UrlToList;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

@ContentView(R.layout.fragment_image)
public class FragmentImage extends BaseFragment {
    @ViewInject(R.id.second_viewpagerone)
    ViewPager viewPagerTop;
    @ViewInject(R.id.linear_point_mian)
    LinearLayout pointGroup;
    @ViewInject(R.id.iv_recomone)
    ImageView iv_recomone;
    @ViewInject(R.id.iv_recomtwo)
    ImageView iv_recomtwo;
    @ViewInject(R.id.iv_recomthree)
    ImageView iv_recomthree;
    @ViewInject(R.id.iv_recomfour)
    ImageView iv_recomfour;
    @ViewInject(R.id.pageonre_lv)
    CustomMyListView pageonre_lv;
    private boolean isLoop = true;
    private int lastPosition;
    private ArrayList<ImageView> viewpagerList;
    private int[] imageList = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3,};
    private MyHeaderPageAdapter headAdapter;
    private   PageoneRecomAdapter adapter;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:
                    if (isLoop) {

                        viewPagerTop.setCurrentItem(viewPagerTop.getCurrentItem() + 1, true);
                    }

                    break;

                default:
                    break;
            }

        }

        ;
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return x.view().inject(this, inflater, container);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vpData();
        initLvclick();
        loopPoint();

    }

    private void initLvclick() {
        pageonre_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pageonelv item = (Pageonelv)adapter.getItem(position);
                String objectId = item.getObjectId();
                Intent intent  =  new Intent(getActivity(),ActivityGoods.class);
                intent.putExtra("objectId",objectId);
                intent.putExtra("imageurl",item.getlvimage().getFileUrl(getContext()));
                startActivity(intent);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isLoop = false;
    }


    public void vpData() {
        //=======这里是viewpager的图片
        BmobQuery<Pageonevp> bmobQuery = new BmobQuery<Pageonevp>();
        bmobQuery.findObjects(getActivity(),
                new FindListener<Pageonevp>() {
                    @Override
                    public void onSuccess(final List<Pageonevp> list) {
                      // saveImageUrl(getActivity(), hotelone_ivone, list.get(0).getHimage().getFileUrl(getActivity()));
                        //x.image().bind(hotelone_ivone,"http://bmob-cdn-3171.b0.upaiyun.com/2016/07/20/03f4839a403b61f880fbdbc42cf73cff.jpg");
                        Log.i("====当前的线程", Thread.currentThread() + "");
                        //diaplayImage();


                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                initViewpager(list);
                              //  displayImage();
                            }
                        });
                    }

                    @Override
                    public void onError(int arg0, String arg1) {
                        Log.i("====图片地址错误174", arg1 + "arg0" + arg0);


                    }
                });
      //  displayImage();
        //========================轮播下面的推荐四张图片
        BmobQuery<Pageonere> query = new BmobQuery<Pageonere>();
        query.findObjects(getActivity(),
                new FindListener<Pageonere>() {
                    @Override
                    public void onSuccess(final List<Pageonere> list) {
                        Tools.saveImageUrl(getActivity(), iv_recomone, list.get(0).getimage().getFileUrl(getActivity()));
                        Tools.saveImageUrl(getActivity(), iv_recomtwo, list.get(1).getimage().getFileUrl(getActivity()));
                        Tools.saveImageUrl(getActivity(), iv_recomthree, list.get(2).getimage().getFileUrl(getActivity()));
                        Tools.saveImageUrl(getActivity(), iv_recomfour, list.get(3).getimage().getFileUrl(getActivity()));
                        //x.image().bind(hotelone_ivone,"http://bmob-cdn-3171.b0.upaiyun.com/2016/07/20/03f4839a403b61f880fbdbc42cf73cff.jpg");
                        Log.i("====当前的线程", Thread.currentThread() + "");
                        displayImage();
                    }
                    @Override
                    public void onError(int arg0, String arg1) {
                        Log.i("====图片地址错误174", arg1 + "arg0" + arg0);
                    }
                });
        //========================listview的数据
        BmobQuery<Pageonelv> lvquery = new BmobQuery<Pageonelv>();
        lvquery.findObjects(getActivity(),
                new FindListener<Pageonelv>() {
                    @Override
                    public void onSuccess(final List<Pageonelv> list) {
                        adapter = new PageoneRecomAdapter(list, getActivity());
                        //x.image().bind(hotelone_ivone,"http://bmob-cdn-3171.b0.upaiyun.com/2016/07/20/03f4839a403b61f880fbdbc42cf73cff.jpg");
                        Log.i("====当前的线程", Thread.currentThread() + "");
                        pageonre_lv.setAdapter(adapter);
                    }
                    @Override
                    public void onError(int arg0, String arg1) {
                        Log.i("====图片地址错误174", arg1 + "arg0" + arg0);
                    }
                });

        displayImage();
    }

    private void displayImage() {
        UrlToList.urlDisplayImage(iv_recomone, getActivity());
        UrlToList.urlDisplayImage(iv_recomthree, getActivity());
        UrlToList.urlDisplayImage(iv_recomtwo, getActivity());
        UrlToList.urlDisplayImage(iv_recomfour, getActivity());
    }

    //初始化上方viewpager图片切换
    private void initViewpager(List<Pageonevp> list) {
        viewpagerList = new ArrayList<>();
        initViewPagerScroll(viewPagerTop);
        for (int i = 0; i <list.size(); i++) {
            //初始化图片资源
            final ImageView image = new ImageView(getActivity());
            image.setId(0x7f0c006+i);
            image.setTag(i);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int j = (int) v.getTag();

                }
            });
            Log.i("===轮播图的地址",list.get(i).getHimage().getFileUrl(getActivity()));
            Tools.saveImageUrl(getActivity(), image, list.get(i).getHimage().getFileUrl(getActivity()));
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            image.setLayoutParams(layoutParams);
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            UrlToList.urlDisplayImage(image, getActivity());
            //image.setImageResource(imageList[i]);
            viewpagerList.add(image);
            // 添加指示点
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.rightMargin = 20;
            point.setLayoutParams(params);

            point.setBackgroundResource(R.drawable.point_bg);
            if (i == 0) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }
            pointGroup.addView(point);
        }
        headAdapter = new MyHeaderPageAdapter(viewpagerList);
        viewPagerTop.setAdapter(headAdapter);
        setChangeListener();

    }
    private void setChangeListener() {
        viewPagerTop.setPageTransformer(true,new DepthPageTransformer());
        viewPagerTop.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {

                position = position%viewpagerList.size();



                //改变指示点的状态
                //把当前点enbale 为true
                pointGroup.getChildAt(position).setEnabled(true);
                //把上一个点设为false
                pointGroup.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }



    // ============轮询播放viewpager
    private void loopPoint() {
        new Thread() {
            @Override
            public void run() {
                while (isLoop) {

                    SystemClock.sleep(4500);
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();

    }


    public void initViewPagerScroll(ViewPager viewPager) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller( getActivity() );
            mScroller.set( viewPager, scroller);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}

