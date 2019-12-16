package com.example.sammy.recyclerviewtest;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

//CustomViewHolder -> 내가 지은 이름
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {
    //1. context : 제공이 된다.(onCreateViewHolder에서 제공(ViewGroup 제공))
    //2. layout
    int layout;
    //3. 리스트
    ArrayList<MainData> list;
    //4. 인플레이터 X
   // LayoutInflater layoutInflater;

    //생성자에서 데이터 리스트 객체를 전달받음
    public MainAdapter(int layout, ArrayList<MainData> list) {
        this.layout = layout;
        this.list = list;
    }

    //1. 뷰홀더 객체 생성
    //viewHolder에 있는 layout화면을 객체화한다. 해당된 viewHolder 리턴
    @NonNull
    @Override
    //== getView와 같다
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //view를 inflate시키고
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        //customViewHolder에 저장된 값을 여기서 돌려준다.
        CustomViewHolder viewHolder=new CustomViewHolder(view);
        return viewHolder;
    }

    //2. 데이터를 뷰홀더에 바인딩
    //position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder customViewHolder, final int position) {
        //이미 customViewHolder값을 밑에서 찾아서 저장해놨기 때문에 id찾을 필요 없이 바로 사용.
        customViewHolder.imgProfile.setImageResource(list.get(position).getImgProfile());
        customViewHolder.txtName.setText(list.get(position).getTxtName());
        customViewHolder.txtContent.setText(list.get(position).getTxtContent());
        //한 칸을 같이 묶어서 이벤트 처리 가능하게끔 세팅함
        customViewHolder.itemView.setTag(position);

        //여기서 액션을 취한다.
        customViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentName = customViewHolder.txtName.getText().toString().trim();
                Toast.makeText(v.getContext(), currentName, Toast.LENGTH_SHORT).show();
            }
        });

        //롱클릭시 삭제
        customViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                list.remove(position);
                //반영, 빨리 다시 리사이클러뷰
                notifyDataSetChanged();
                Toast.makeText(v.getContext(), list.get(position).getTxtName()+" 삭제완료",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    //3. 전체 아이템 갯수 리턴, 리스트의 사이즈를 준다.
    @Override
    public int getItemCount() {
        return (list != null)? list.size() : 0;
    }
    //findViewId를 여기서 한다. 위에서 viewHolder를 받음
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgProfile;
        public TextView txtName;
        public TextView txtContent;

        //itemView에는 viewHolder 객체가 된 레이아웃 주소가 전달이 된다.
        //찾은 객체를 저장, 딱 한번만 찾아서 값을 줌
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile=itemView.findViewById(R.id.imgProfile);
            txtName=itemView.findViewById(R.id.txtName);
            txtContent=itemView.findViewById(R.id.txtContent);
        }
    }
}
//리스트뷰의 2개가 빠짐