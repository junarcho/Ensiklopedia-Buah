package com.example.ensiklopedia_buah

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ensiklopedia_buah.adapter.CardViewFruitAdapter
import com.example.ensiklopedia_buah.adapter.GridFruitAdapter
import com.example.ensiklopedia_buah.adapter.ListFruitAdapter
import com.example.ensiklopedia_buah.entity.Fruits
import com.example.ensiklopedia_buah.entity.FruitsData
import com.miguelcatalan.materialsearchview.MaterialSearchView
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var searchView: MaterialSearchView

    private lateinit var rvFruit: RecyclerView
    private var list: ArrayList<Fruits> = arrayListOf()
    private var fruitsData: ArrayList<Fruits> = FruitsData.listData

    private var title: String = "Ensiklopedia Buah"

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        setActionBarTitle(title)
        searchView  = findViewById(R.id.search_view)
        searchView.setCursorDrawable(R.drawable.custom_cursor)
        searchView.setEllipsize(true)


        rvFruit = findViewById(R.id.rv_fruits)
        rvFruit.setHasFixedSize(true)

        list.addAll(fruitsData)
        showRecyclerList()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem: MenuItem = menu.findItem(R.id.action_search)
        searchView.setMenuItem(menuItem)

        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText!!.isNotEmpty()) {

                    list.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    fruitsData.forEach {

                        if (it.name.toLowerCase(Locale.getDefault()).contains(search)) {
                            list.add(it)
                        }
                    }

                    rvFruit.adapter!!.notifyDataSetChanged()
                }

                else {

                    list.clear()
                    list.addAll(fruitsData)
                    rvFruit.adapter!!.notifyDataSetChanged()
                }

                return true
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        if(searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }

   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {

        when(selectedMode) {

            R.id.action_list -> {
                showRecyclerList()
            }

            R.id.action_grid -> {
                showRecyclerGrid()
            }

            R.id.action_cardview -> {
                showRecycleCardView()
            }

            R.id.about -> {
                showAboutApp()
            }
        }

    }

    private fun showRecyclerList() {
        rvFruit.layoutManager = LinearLayoutManager(this)
        val listFruitAdapter = ListFruitAdapter(list)
        rvFruit.adapter = listFruitAdapter

        listFruitAdapter.setOnItemClickCallback(object : ListFruitAdapter.OnItemClickCallback {
            override fun onItemCliked(data: Fruits) {

                val detailActivityIntent =
                    Intent(this@MainActivity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_IMAGE, data.photo)
                        putExtra(DetailActivity.EXTRA_NAME, data.name)
                        putExtra(DetailActivity.EXTRA_ENGLISH, data.english_name)
                        putExtra(DetailActivity.EXTRA_DETAIL, data.detail)
                        putExtra(DetailActivity.EXTRA_BENEFIT, data.benefits)
                    }
                showSelectedFruit(data)
                startActivity(detailActivityIntent)
            }
        })
    }

    private fun showRecyclerGrid() {
        rvFruit.layoutManager = GridLayoutManager(this, 2)
        val gridFruitAdapter = GridFruitAdapter(list)
        rvFruit.adapter = gridFruitAdapter

        gridFruitAdapter.setOnItemClickCallback(object : GridFruitAdapter.OnItemClickCallback {
            override fun onItemCliked(data: Fruits) {
                showSelectedFruit(data)
            }
        })
    }

    private fun showRecycleCardView() {
        rvFruit.layoutManager = LinearLayoutManager(this)
        val cardViewFruitAdapter = CardViewFruitAdapter(list)
        rvFruit.adapter = cardViewFruitAdapter
    }

    private fun showAboutApp() {
        val moveIntent = Intent(this@MainActivity, AboutAppActivity::class.java)
        startActivity(moveIntent)
    }

    private fun showSelectedFruit(fruits: Fruits) {

        val fruit: String = fruits.name
        Toast.makeText(this, "Kamu Memilih $fruit", LENGTH_SHORT).show()
    }
}