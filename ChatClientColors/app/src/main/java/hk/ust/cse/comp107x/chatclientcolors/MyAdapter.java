package hk.ust.cse.comp107x.chatclientcolors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final ArrayList<Message> messages;

    private static final int VIEW_HOLDER_OUTGOING_MSG =1;
    private static final int VIEW_HOLDER_INCOMING_MSG=2;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder_OutGoing extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mymessageTextView, mytimeTextView;
        public ViewHolder_OutGoing(View v) {
            super(v);
            this.mymessageTextView = (TextView) v.findViewById(R.id.mymessageTextView);
            this.mytimeTextView = (TextView) v.findViewById(R.id.mytimeTextView);
        }


    }

    public static class ViewHolder_Type2 extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView messageTextView, timeTextView;
        public ViewHolder_Type2(View v) {
            super(v);
            this.messageTextView = (TextView) v.findViewById(R.id.messageTextView);
            this.timeTextView = (TextView) v.findViewById(R.id.timeTextView);
        }


    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        View v;

        switch (viewType) {
            // create a new view
            case VIEW_HOLDER_OUTGOING_MSG:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.mymessage, parent, false);
                return new ViewHolder_OutGoing(v);

            case VIEW_HOLDER_INCOMING_MSG:
                v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.message, parent, false);
                return new ViewHolder_Type2(v);
            default:
                break;
        }

        return null;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        switch (getItemViewType(position)) {

            case VIEW_HOLDER_OUTGOING_MSG:
                ViewHolder_OutGoing viewholder1 = (ViewHolder_OutGoing) holder;
                TextView mytimeView = (TextView) viewholder1.mytimeTextView;
                mytimeView.setText(messages.get(position).getTime());
                TextView mymsgView = (TextView) viewholder1.mymessageTextView;
                mymsgView.setText(messages.get(position).getMessage());
                break;

            case VIEW_HOLDER_INCOMING_MSG:
                ViewHolder_Type2 viewholder2 = (ViewHolder_Type2) holder;
                TextView timeView = (TextView) viewholder2.timeTextView;
                timeView.setText(messages.get(position).getTime());
                TextView msgView = (TextView) viewholder2.messageTextView;
                msgView.setText(messages.get(position).getMessage());
                break;

            default:
                break;
        }

    }



    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 1 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous
        if (messages.get(position).fromMe())
            return VIEW_HOLDER_OUTGOING_MSG;
        else
            return VIEW_HOLDER_INCOMING_MSG;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return messages.size();
    }
}