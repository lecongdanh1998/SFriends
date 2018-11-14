package vn.edu.poly.sfriends.View.HomePage.Sagora.classifieds.rent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import vn.edu.poly.sfriends.Adapter.AdapterClassified;
import vn.edu.poly.sfriends.Model.ClassufiedContructor;
import vn.edu.poly.sfriends.R;

public class rent extends Fragment {
    private View view;
    AdapterClassified adapter;
    ArrayList<ClassufiedContructor> arrayList;
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_rent_classified, container, false);
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
        listView = view.findViewById(R.id.listview_rent);

    }

    private void initData() {
        arrayList = new ArrayList<>();
        arrayList.add(new ClassufiedContructor("https://media-cdn.tripadvisor.com/media/photo-s/0f/a5/6e/32/sea-snail-homestay.jpg"
                ,"Lưu liền tay list homestay Sapa đẹp hút hồn “quên cả lối về”"
                ,"Là một căn nhà nhỏ cuối đường Hoàng Liên Sơn, VietTrekking homestay Sapa chính là một địa chỉ không thể tuyệt hơn với du khách, đặc biệt là phượt thủ yêu thích cảm giác như được hoà vào mây trời chốn bồng lai."
                ,"Fed 18, 4:58 PM"
                ,"3,17,00,000"
        ));
        arrayList.add(new ClassufiedContructor("http://canhodatnen.com.vn/Upload/files/homestay.jpg"
                ,"Lưu liền tay list homestay Sapa đẹp hút hồn “quên cả lối về”"
                ,"Là một căn nhà nhỏ cuối đường Hoàng Liên Sơn, VietTrekking homestay Sapa chính là một địa chỉ không thể tuyệt hơn với du khách, đặc biệt là phượt thủ yêu thích cảm giác như được hoà vào mây trời chốn bồng lai."
                ,"Fed 18, 4:58 PM"
                ,"1,98,00,000"
        ));
        arrayList.add(new ClassufiedContructor("https://s-ec.bstatic.com/images/hotel/max1024x768/130/130746442.jpg"
                ,"Lưu liền tay list homestay Sapa đẹp hút hồn “quên cả lối về”"
                ,"Là một căn nhà nhỏ cuối đường Hoàng Liên Sơn, VietTrekking homestay Sapa chính là một địa chỉ không thể tuyệt hơn với du khách, đặc biệt là phượt thủ yêu thích cảm giác như được hoà vào mây trời chốn bồng lai."
                ,"Fed 18, 4:58 PM"
                ,"1,26,00,000"
        ));
        arrayList.add(new ClassufiedContructor("https://vtv1.mediacdn.vn/zoom/550_339/2018/7/4/van-hoa-homestay-viet-nam-52-crop-15306439064012092143059.jpg"
                ,"Lưu liền tay list homestay Sapa đẹp hút hồn “quên cả lối về”"
                ,"Là một căn nhà nhỏ cuối đường Hoàng Liên Sơn, VietTrekking homestay Sapa chính là một địa chỉ không thể tuyệt hơn với du khách, đặc biệt là phượt thủ yêu thích cảm giác như được hoà vào mây trời chốn bồng lai."
                ,"Fed 18, 4:58 PM"
                ,"2,17,00,000"
        ));
        arrayList.add(new ClassufiedContructor("https://media-cdn.tripadvisor.com/media/photo-s/09/02/14/03/skalatjorn-homestay.jpg"
                ,"Lưu liền tay list homestay Sapa đẹp hút hồn “quên cả lối về”"
                ,"Là một căn nhà nhỏ cuối đường Hoàng Liên Sơn, VietTrekking homestay Sapa chính là một địa chỉ không thể tuyệt hơn với du khách, đặc biệt là phượt thủ yêu thích cảm giác như được hoà vào mây trời chốn bồng lai."
                ,"Fed 18, 4:58 PM"
                ,"1,17,00,000"
        ));
        adapter = new AdapterClassified(arrayList, getContext());
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
