package com.ghroosk.priactice.view.letter.index;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ghroosk.priactice.view.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by zhx on 2019/3/12.
 */
public class LetterIndexAct extends AppCompatActivity {

    private static final String TAG = LetterIndexAct.class.getSimpleName();
    ListView mListView;
    IndexScroller mIndexScroller;
    Toast mToast;

    private List<Person> mDataList;
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("");
        setContentView(R.layout.activity_letter_index);

        mIndexScroller = findViewById(R.id.scroll);

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        initListView();

        initData();
    }


    private void initData() {
        mIndexScroller = findViewById(R.id.scroll);
        mIndexScroller.setOnWordsChangeListener(new IndexScroller.onWordsChangeListener() {
            @Override
            public void wordsChange(String words) {
                Log.e(TAG, "wordsChange: " + words);
                updateListView(words);
            }
        });
    }

    /**
     * @param words 首字母
     */
    private void updateListView(String words) {
        if (mToast != null) {
            mToast.setText(words);
            mToast.setGravity(Gravity.CENTER, 0, 0);
            mToast.show();
        }
        for (int i = 0; i < mDataList.size(); i++) {
            String headerWord = mDataList.get(i).getHeaderWord();
            //将手指按下的字母与列表中相同字母开头的项找出来
            if (words.equals(headerWord)) {
                //将列表选中哪一个
                mListView.setSelection(i);
                //找到开头的一个即可
                return;
            }
        }
    }

    private final String mWorks[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private void initListView() {
        mListView = findViewById(R.id.common_driver);
        mDataList = new ArrayList<Person>();
        for (int i = 0, length = mWorks.length; i < length; i++) {
            for (int j = 0; j < 4; j++) {
                Person person = new Person(mWorks[i] + i);
                mDataList.add(person);
            }
        }
        mAdapter = new MyAdapter(this, mDataList);
        mListView.setAdapter(mAdapter);
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //当滑动列表的时候，更新右侧字母列表的选中状态
                mIndexScroller.setTouchIndex(mDataList.get(firstVisibleItem).getHeaderWord());
            }
        });
    }

    class Person {
        //姓名
        private String name;
        //拼音
        private String pinyin;

        //拼音首字母
        private String headerWord;

        public Person(String name) {
            this.name = name;
            this.pinyin = PinYin.getPinYin(name);
            headerWord = pinyin.substring(0, 1);
        }

        public String getPinyin() {
            return pinyin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeaderWord() {
            return headerWord;
        }
    }

    class MyAdapter extends BaseAdapter {
        private List<Person> list;
        private LayoutInflater inflater;

        public MyAdapter(Context context, List<Person> list) {
            inflater = LayoutInflater.from(context);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.list_item, null);
                holder.tv_word = (TextView) convertView.findViewById(R.id.tv_word);
                holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String word = list.get(position).getHeaderWord();
            holder.tv_word.setText(word);
            holder.tv_name.setText(list.get(position).getName());
            //将相同字母开头的合并在一起
            if (position == 0) {
                //第一个是一定显示的
                holder.tv_word.setVisibility(View.VISIBLE);
            } else {
                //后一个与前一个对比,判断首字母是否相同，相同则隐藏
                String headerWord = list.get(position - 1).getHeaderWord();
                if (word.equals(headerWord)) {
                    holder.tv_word.setVisibility(View.GONE);
                } else {
                    holder.tv_word.setVisibility(View.VISIBLE);
                }
            }
            return convertView;
        }

        private class ViewHolder {
            private TextView tv_word;
            private TextView tv_name;
        }
    }


}