package vn.edu.poly.sfriends.View.HomePage.Sarena.ALL_CONVERSATION;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterAllConverSation;
import vn.edu.poly.sfriends.Model.AllConverSationContructor;
import vn.edu.poly.sfriends.R;

public class all_conversation extends Fragment {
    private View view;
    AdapterAllConverSation adapter;
    ArrayList<AllConverSationContructor> arrayList;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_all_conversation, container, false);
        initControl();
        initData();
        initEvent();
        initOnClick();
        return view;
    }

    private void initEvent() {
    }

    private void initOnClick() {
    }

    private void initControl() {
        listView = view.findViewById(R.id.listview_all_conversation);
    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new AllConverSationContructor("https://media.ngoisao.vn/resize_580/news/2017/05/25/em-gai-yumi-duong-ngoisaovn-7-ngoisao.vn-w639-h960.stamp2.jpg"
                , "PARAKASM IM"
                , "(A-123)"
                , "Jan 20, 4:03 PM"
                , "Who to get as Chief Guest? (All Members)"
                , "86"
                , "25"
                , ""
                , ""
                , ""
                , "Independence Day Celebration !!"
                , "Maths Tuition in B wing"
                , "2 days ago"
                , "1 hour ago"));
        arrayList.add(new AllConverSationContructor("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_LIHxDDkUImbNAoMjH5_FiQ876YQxpzMAwoMkVo-R4eDmQxkz"
                , "Lalita Murthy"
                , "(A-13)"
                , "12/22/2008, 8:35 AM"
                , "Can you suggest me a good water purifier for our home?"
                , ""
                , ""
                , "I am still try ing to make up my mind, shop..."
                , "86",
                "25"
                , ""
                , ""
                , ""
                , ""));
        arrayList.add(new AllConverSationContructor("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_LIHxDDkUImbNAoMjH5_FiQ876YQxpzMAwoMkVo-R4eDmQxkz"
                , "Lalita Murthy David"
                , "(A-19)"
                , "5/1/2017, 3:35 PM"
                , "Can you suggest me a good water purifier for our home?"
                , ""
                , ""
                , "I am still try ing to make up my mind, shop"
                , "86",
                "25", "Independence Day Celebration !!"
                , "Maths Tuition in B wing"
                , "2 days ago"
                , "1 hour ago"));
        arrayList.add(new AllConverSationContructor("https://media.ngoisao.vn/resize_580/news/2017/05/25/em-gai-yumi-duong-ngoisaovn-7-ngoisao.vn-w639-h960.stamp2.jpg"
                , "PARAKASM IM"
                , "(A-123)"
                , "Jan 20, 4:03 PM"
                , "Who to get as Chief Guest? (All Members)"
                , "50"
                , "79"
                , ""
                , "",
                "",""
                ,""
                ,""
                ,""));
        adapter = new AdapterAllConverSation(arrayList, getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
