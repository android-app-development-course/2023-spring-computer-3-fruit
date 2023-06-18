package com.king.template.app.account

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.king.template.R


class Temp : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        Log.e("TAG","position: "+p2+",content: "+p0!!.getItemAtPosition(p2).toString())
    }

    val fruits = ArrayList<String>()
    val weights = ArrayList<String>()
    val myMap = mutableMapOf<String, Int>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        var selectedFruit: String = ""
        var selectedWeight: String = ""
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temp)

        fruits.add("苹果")
        fruits.add("梨")
        fruits.add("香蕉")

        val adapter1 = ArrayAdapter(this, R.layout.color_spinner_layout, fruits)
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        val fruit = findViewById<Spinner>(R.id.fruit)
        fruit.adapter = adapter1
        fruit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // 在这里使用选中项的值
                selectedFruit = parent.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 当没有选择时的处理
            }
        }

        weights.add("100g")
        weights.add("200g")
        weights.add("300g")
        weights.add("400g")

        val adapter2 = ArrayAdapter(this, R.layout.color_spinner_layout, weights)
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_layout)
        val weight = findViewById<Spinner>(R.id.weight)
        weight.adapter = adapter2
        weight.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                // 在这里使用选中项的值
                selectedWeight = parent.getItemAtPosition(position) as String
                val pattern = "\\d+".toRegex()
                val matchResult = pattern.find(selectedWeight)
                val myInt = matchResult?.value?.toInt() ?: 0
                myMap[selectedFruit] = myInt
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // 当没有选择时的处理
            }
        }

        val parentLayout = findViewById<LinearLayout>(R.id.parentLayout)
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout1)
        val addButton = findViewById<Button>(R.id.addButton)

        addButton.setOnClickListener {
            var newFruit: String = ""
            var newWeight: String = ""
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(45, 50, 15, 0)

            val textViewLayoutWidth = linearLayout.getChildAt(0).layoutParams.width
            val spinnerLayoutWidth = linearLayout.getChildAt(1).layoutParams.width

            val newLinearLayout1 = LinearLayout(this)
            newLinearLayout1.orientation = LinearLayout.HORIZONTAL
            newLinearLayout1.layoutParams = layoutParams

            val newTextView1 = TextView(this)
            newTextView1.layoutParams = LinearLayout.LayoutParams(
                textViewLayoutWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newTextView1.text = "水果名:"
            newTextView1.textSize = 20f

            val newSpinner1 = Spinner(this)
            newSpinner1.layoutParams = LinearLayout.LayoutParams(
                spinnerLayoutWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newSpinner1.background = ContextCompat.getDrawable(this, R.drawable.round)
            newSpinner1.adapter = adapter1
            newSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // 在这里使用选中项的值
                    newFruit = parent.getItemAtPosition(position) as String
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // 当没有选择时的处理
                }
            }

            newLinearLayout1.addView(newTextView1)
            newLinearLayout1.addView(newSpinner1)

            val newLinearLayout2 = LinearLayout(this)
            newLinearLayout2.orientation = LinearLayout.HORIZONTAL
            newLinearLayout2.layoutParams = layoutParams

            val newTextView2 = TextView(this)
            newTextView2.layoutParams = LinearLayout.LayoutParams(
                textViewLayoutWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newTextView2.text = "重    量:"
            newTextView2.textSize = 20f

            val newSpinner2 = Spinner(this)
            newSpinner2.layoutParams = LinearLayout.LayoutParams(
                spinnerLayoutWidth,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            newSpinner2.background = ContextCompat.getDrawable(this, R.drawable.round)
            newSpinner2.adapter = adapter2
            newSpinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @RequiresApi(Build.VERSION_CODES.N)
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // 在这里使用选中项的值
                    newWeight = parent.getItemAtPosition(position) as String
                    val pattern = "\\d+".toRegex()
                    val matchResult = pattern.find(newWeight)
                    val myInt = matchResult?.value?.toInt() ?: 0
                    myMap[newFruit] = myInt
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // 当没有选择时的处理
                }
            }

            newLinearLayout2.addView(newTextView2)
            newLinearLayout2.addView(newSpinner2)

            parentLayout.addView(newLinearLayout1)
            parentLayout.addView(newLinearLayout2)
        }

        val confirm = findViewById<Button>(R.id.confirmButton)
        confirm.setOnClickListener {
            val Fibers = arrayOf(2.1, 3.4, 2.6)
            val Proteins = arrayOf(0.3, 0.4, 1.1)
            val Fats = arrayOf(0.2, 0.1, 0.3)
            val VitaminC = arrayOf(4, 3, 9)

            val appleWeight = myMap["苹果"]?.toDouble() ?: 0.0 // 苹果的重量（单位：克）
            val pearWeight = myMap["梨"]?.toDouble() ?: 0.0 // 梨的重量（单位：克）
            val bananaWeight = myMap["香蕉"]?.toDouble() ?: 0.0 // 香蕉的重量（单位：克）

            val appleFiberAmount = appleWeight * Fibers[0] / 100.0 // 苹果的纤维素含量（单位：克）
            val pearFiberAmount = pearWeight * Fibers[1] / 100.0 // 梨的纤维素含量（单位：克）
            val bananaFiberAmount = bananaWeight * Fibers[2] / 100.0 // 香蕉的纤维素含量（单位：克）
            val totalFiberAmount = appleFiberAmount + pearFiberAmount + bananaFiberAmount // 三个水果的总纤维素含量（单位：克）

            val appleProteinAmount = appleWeight * Proteins[0] / 100.0 // 苹果的蛋白质含量（单位：克）
            val pearProteinAmount = pearWeight * Proteins[1] / 100.0 // 梨的蛋白质含量（单位：克）
            val bananaProteinAmount = bananaWeight * Proteins[2] / 100.0 // 香蕉的蛋白质含量（单位：克）
            val totalProteinAmount = appleProteinAmount + pearProteinAmount + bananaProteinAmount // 三个水果的总蛋白质含量（单位：克）

            val appleFatAmount = appleWeight * Fats[0] / 100.0 // 苹果的脂肪含量（单位：克）
            val pearFatAmount = pearWeight * Fats[1] / 100.0 // 梨的脂肪含量（单位：克）
            val bananaFatAmount = bananaWeight * Fats[2] / 100.0 // 香蕉的脂肪含量（单位：克）
            val totalFatAmount = appleFatAmount + pearFatAmount + bananaFatAmount // 三个水果的总脂肪含量（单位：克）

            val appleVitaminCAmount = appleWeight * VitaminC[0] / 100.0 // 苹果的脂肪含量（单位：克）
            val pearVitaminCAmount = pearWeight * VitaminC[1] / 100.0 // 梨的脂肪含量（单位：克）
            val bananaVitaminCAmount = bananaWeight * VitaminC[2] / 100.0 // 香蕉的脂肪含量（单位：克）
            val totalVitaminCAmount = appleVitaminCAmount + pearVitaminCAmount + bananaVitaminCAmount // 三个水果的总脂肪含量（单位：克）

            // 初始化柱形图
            val barChart: BarChart = findViewById(R.id.bar_chart)
            barChart.apply {
                setDrawBarShadow(false)
                setDrawValueAboveBar(true)
                description.isEnabled = false
                setMaxVisibleValueCount(60)
                setPinchZoom(false)
                setDrawGridBackground(false)
                legend.isEnabled = false
            }

            // 设置 x 轴标签
            val xLabels = listOf("纤维素", "蛋白质", "脂肪", "维生素C")
            barChart.xAxis.apply {
                granularity = 1f
                setCenterAxisLabels(true)
                setDrawGridLines(false)
                position = XAxis.XAxisPosition.BOTTOM
                valueFormatter = IndexAxisValueFormatter(xLabels)
                axisMinimum = 0f
            }

            // 设置 y 轴数据
            val yVals = listOf(
                BarEntry(0.5f, totalFiberAmount.toFloat()),
                BarEntry(1.5f, totalFatAmount.toFloat()),
                BarEntry(2.5f, totalFatAmount.toFloat()),
                BarEntry(3.5f, totalVitaminCAmount.toFloat())
            )

            // 设置柱形图数据
            val barDataSet = BarDataSet(yVals, "").apply {
                colors = listOf(Color.BLUE, Color.RED, Color.GREEN, Color.YELLOW)
                valueTextSize = 16f
                setDrawIcons(false)
            }
            val barData = BarData(barDataSet).apply {
                barWidth = 0.5f
            }
            barChart.data = barData

            // 添加动画效果
            barChart.animateY(1000)
        }
    }
}