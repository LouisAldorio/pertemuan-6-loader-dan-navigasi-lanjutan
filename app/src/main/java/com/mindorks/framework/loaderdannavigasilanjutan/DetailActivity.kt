package com.mindorks.framework.loaderdannavigasilanjutan

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.widget.Gallery
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor>{

    var DisplayName = ContactsContract.Contacts.DISPLAY_NAME
    var Number = ContactsContract.CommonDataKinds.Phone.NUMBER
    var myListContact : MutableList<myContact> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        LoaderManager.getInstance(this).initLoader(1101,null,this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {

        var MyContactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        var MyProjection = arrayOf(DisplayName,Number)

        return CursorLoader(this,MyContactUri,MyProjection,null,null,"${DisplayName} ASC")
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        myListContact.clear()
        if(data != null) {
            data.moveToFirst()
            while (!data.isAfterLast){
                myListContact.add(myContact(
                        data.getString(data.getColumnIndex(DisplayName)),
                        data.getString(data.getColumnIndex(Number))
                    )
                )
                data.moveToNext()
            }

            myRecyView.apply {
                adapter = myAdapterRecycleView(myListContact)
                layoutManager = LinearLayoutManager(this@DetailActivity)
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        myRecyView.adapter?.notifyDataSetChanged()
    }
}