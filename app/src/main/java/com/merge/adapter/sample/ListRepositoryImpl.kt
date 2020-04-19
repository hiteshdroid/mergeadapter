package com.merge.adapter.sample

import android.content.Context
import com.merge.adapter.sample.model.repository.ListRepository
import com.merge.adapter.sample.viewmodel.BaseTemplate
import com.merge.adapter.sample.model.data.ListItem
import org.json.JSONArray
import org.json.JSONObject

class ListRepositoryImpl(val applicationContext: Context) : ListRepository {
    override fun loadList(): MutableList<BaseTemplate> {
        val jsonStringFromAssets = loadJSONFromAssets("list.json")
        val templateRoot = JSONObject(jsonStringFromAssets)
        val templateListArray = templateRoot.optJSONArray("list")
        val templateList = mutableListOf<BaseTemplate>()
        templateListArray?.apply {
            for (i in 0 until length()) {
                val jsonObject = optJSONObject(i)
                val templateType = jsonObject.optInt("type")
                val items = jsonObject.optJSONArray("items")
                when (templateType) {
                    Constants.TEMPLATES.HORIZONTAL.ordinal -> {
                        addHorizontalTemplateItems(templateList, items)
                    }
                    Constants.TEMPLATES.VERTICAL.ordinal -> {
                        addVerticalTemplateItems(templateList, items)
                    }
                    Constants.TEMPLATES.GRID.ordinal -> {
                        addGridTemplateItems(templateList, items)
                    }
                    Constants.TEMPLATES.SINGLE.ordinal -> {
                        addSingleTemplateItems(templateList, items)
                    }
                }
            }
        }
        return templateList
    }

    private fun addSingleTemplateItems(templateList: MutableList<BaseTemplate>, items: JSONArray?) {

        items?.apply {
            val listItems = mutableListOf<ListItem>()
            for (i in 0 until length()) {
                val listItem = optJSONObject(i)
                listItems.add(
                    ListItem(
                        listItem.optString("id"),
                        listItem.optString("title"),
                        listItem.optString("desc"),
                        listItem.optString("img")
                    )
                )
            }
            templateList.add(BaseTemplate.SingleItem(listItems[0]))
        }
    }

    private fun addGridTemplateItems(templateList: MutableList<BaseTemplate>, items: JSONArray?) {
        items?.apply {
            val listItems = mutableListOf<ListItem>()
            for (i in 0 until length()) {
                val listItem = optJSONObject(i)
                listItems.add(
                    ListItem(
                        listItem.optString("id"),
                        listItem.optString("title"),
                        listItem.optString("desc"),
                        listItem.optString("img")
                    )
                )
            }
            templateList.add(BaseTemplate.GridList(listItems))
        }
    }

    private fun addVerticalTemplateItems(
        templateList: MutableList<BaseTemplate>,
        items: JSONArray?
    ) {
        items?.apply {
            val listItems = mutableListOf<ListItem>()
            for (i in 0 until length()) {
                val listItem = optJSONObject(i)
                listItems.add(
                    ListItem(
                        listItem.optString("id"),
                        listItem.optString("title"),
                        listItem.optString("desc"),
                        listItem.optString("img")
                    )
                )
            }
            templateList.add(BaseTemplate.VerticalList(listItems))
        }
    }

    private fun addHorizontalTemplateItems(
        templateList: MutableList<BaseTemplate>,
        items: JSONArray?
    ) {
        items?.apply {
            val listItems = mutableListOf<ListItem>()
            for (i in 0 until length()) {
                val listItem = optJSONObject(i)
                listItems.add(
                    ListItem(
                        listItem.optString("id"),
                        listItem.optString("title"),
                        listItem.optString("desc"),
                        listItem.optString("img")
                    )
                )
            }
            templateList.add(BaseTemplate.HorizontalList(listItems))
        }
    }

    private fun loadJSONFromAssets(fileName: String): String {
        return applicationContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}