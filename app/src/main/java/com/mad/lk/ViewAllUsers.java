package com.mad.lk;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewAllUsers extends AppCompatActivity {

    ListView allUserListView;
    ArrayList<Integer> ids;
    ArrayList<String> usernames;
    ArrayList<Bitmap> images;
    ArrayList<String> emails,passwords;
    DatabaseHelper helper;
    Adapter adapter;

    SearchView userSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_users);

        ids = new ArrayList<>();
        usernames = new ArrayList<>();
        images = new ArrayList<>();
        emails = new ArrayList<>();
        passwords = new ArrayList<>();

        adapter = new Adapter();

        allUserListView = (ListView) findViewById(R.id.allUserListView);
        userSearchView = (SearchView) findViewById(R.id.userSearchView);



        allUserListView.setAdapter(adapter);

        helper = new DatabaseHelper(getApplicationContext());
        Cursor cursor = helper.getAllUserData();
        while (cursor.moveToNext())
        {
            ids.add(cursor.getInt(0));
            usernames.add(cursor.getString(1));
            images.add(helper.getImage(cursor.getBlob(2)));
            emails.add(cursor.getString(3));
            passwords.add(cursor.getString(4));

            allUserListView.invalidateViews();
        }

        userSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                setSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                setSearch(newText);
                return true;
            }
        });


    }
    public class Adapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return ids.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View view = getLayoutInflater().inflate(R.layout.userrow,null);
            final CircleImageView userPicture = view.findViewById(R.id.userPicture);
            TextView userName = view.findViewById(R.id.userName);
            TextView email = view.findViewById(R.id.email);
            ImageView delete= view.findViewById(R.id.delete);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {

                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewAllUsers.this);
                    builder.setTitle("Delete");
                    builder.setMessage("Do you really want to proceed?");
                    builder.setIcon(R.drawable.delete);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.dismiss();
                            if(helper.deleteAUser(ids.get(position).toString()))
                            {
                                ids.remove(position);
                                usernames.remove(position);
                                images.remove(position);
                                emails.remove(position);
                                passwords.remove(position);

                                Toast.makeText(ViewAllUsers.this, "User Has been Deleted", Toast.LENGTH_SHORT).show();
                                allUserListView.invalidateViews();

                            }
                            else
                            {
                                Toast.makeText(ViewAllUsers.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id)
                        {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });

            userPicture.setImageBitmap(images.get(position));
            userName.setText(usernames.get(position));
            email.setText(emails.get(position));
            return view;
        }
    }
    public boolean checkUpperCaseAndLowerCase(String KeyWord,String userName)
    {
        int count2 = 0;
        if (KeyWord.length() <= userName.length())
        {
            for (int i = 0; i < KeyWord.length(); i++)
            {

                Character sample1 = userName.charAt(i);
                Character lowerCase = Character.toLowerCase(sample1);
                Character upperCase = Character.toUpperCase(sample1);


                Character sample2 = KeyWord.charAt(i);
                Character lCase = Character.toLowerCase(sample2);
                Character uCase = Character.toUpperCase(sample2);

                if (lowerCase.equals(uCase) || upperCase.equals(lCase) || lowerCase.equals(lCase) || upperCase.equals(uCase)) {
                    count2++;
                    if (count2 == KeyWord.length())
                    {
                        return true;
                    }

                }


            }
        }
        return false;

    }
    public void setSearch(String keyWord)
    {

        ids.clear();
        usernames.clear();
        images.clear();
        emails.clear();
        passwords.clear();

        if(!keyWord.equals(""))
        {
            Cursor cursor = helper.getAllUserData();
            while (cursor.moveToNext())
            {


                int id =  cursor.getInt(0);
                String username = cursor.getString(1);
                Bitmap image = helper.getImage(cursor.getBlob(2));
                String email = cursor.getString(3);
                String password = cursor.getString(4);



                if(checkUpperCaseAndLowerCase(keyWord,username))
                {
                    ids.add(id);
                    usernames.add(username);
                    images.add(image);
                    emails.add(email);
                    passwords.add(password);
                }




            }
        }
        else
        {
            Cursor cursor = helper.getAllUserData();
            while (cursor.moveToNext())
            {

                int id =  cursor.getInt(0);
                String username = cursor.getString(1);
                Bitmap image = helper.getImage(cursor.getBlob(2));
                String email = cursor.getString(3);
                String password = cursor.getString(4);

                ids.add(id);
                usernames.add(username);
                images.add(image);
                emails.add(email);
                passwords.add(password);

            }
        }

        allUserListView.invalidateViews();
    }

}
