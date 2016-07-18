package com.example.android.pdfrendererbasic;/***
 * pdf
 * <p/>
 * Author                : toobler- MZALIH(salih@toobler.com)
 * Company               : Toobler
 * Email:                : info@toobler.com
 * Web site              : http://www.toobler.com
 * Created               : 18/07/16
 * Description           : MoviesAdapter .
 * ==============================================================================================
 * Change History:
 * ----------------------------------------------------------------------------------------------
 * Sl.No.  Date   			Author    		Description
 * ----------------------------------------------------------------------------------------------
 */

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/***
 * pdf
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{



    public static Adapter  setAdapter(Activity activity,RecyclerView recycler_view ,String filename){

        Adapter mAdapter = new Adapter(activity,filename);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
        return mAdapter;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView)view.findViewById(R.id.image);
        }
    }
    PdfRender renderer;
    public Adapter(Context ctx,String path) {
        renderer = new PdfRender();
        try {
            renderer.openRenderer(ctx,path);

        }catch (Exception e){

        }

    }

    @Override
    public int getItemCount() {
        return renderer.getPageCount();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imagecell, parent, false);

        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView.setImageResource(R.drawable.ic_launcher);
        holder.imageView.setImageBitmap(renderer.getPage(position,holder.imageView.getContext()));
    }
}
