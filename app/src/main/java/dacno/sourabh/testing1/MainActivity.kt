package dacno.sourabh.testing1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvinput: TextView? =null
    var lastnumberic : Boolean=true
    var lastdot : Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvinput=findViewById(R.id.tvinput)
    }



    fun onDigit(view:View){
        tvinput?.append((view as Button).text)
        lastnumberic= true
        lastdot= false
    }
    fun onClear(view:View){
        tvinput?.text=""
    }
    fun onDecimalPoint(view:View){
        if(lastnumberic && !lastdot){
            tvinput?.append(".")
            lastnumberic=false
            lastdot=true
        }
    }
    fun onOperator(view: View)
    {
        tvinput?.text?.let {
            if(lastnumberic && !isOperatorAdded(it.toString())){
                tvinput?.append((view as Button).text)
                lastnumberic = false
                lastdot = false
            }
        }
    }

    fun Onequal(view :View){
        if(lastnumberic){
            var tvValue = tvinput?.text.toString()
            var prefix = ""
            try {

                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one=splitValue[0]
                    var two=splitValue[1]
                    //var result = one.toDouble()-two.toDouble()
                    //tvinput?.text = result.toString()
                    if(prefix.isNotEmpty()){                                        //Doubt
                        one = prefix+one                                             //Doubt
                    }                                                                   //DOubt
                    tvinput?.text = removeZeroAfterDot((one.toDouble()-two.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one=splitValue[0]
                    var two=splitValue[1]
                    //var result = one.toDouble()-two.toDouble()
                    //tvinput?.text = result.toString()
                    if(prefix.isNotEmpty()){                                        //Doubt
                        one = prefix+one                                             //Doubt
                    }                                                                   //DOubt
                    tvinput?.text = removeZeroAfterDot((one.toDouble()+two.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one=splitValue[0]
                    var two=splitValue[1]
                    //var result = one.toDouble()-two.toDouble()
                    //tvinput?.text = result.toString()
                    if(prefix.isNotEmpty()){                                        //Doubt
                        one = prefix+one                                             //Doubt
                    }                                                                   //DOubt
                    tvinput?.text = removeZeroAfterDot((one.toDouble()*two.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one=splitValue[0]
                    var two=splitValue[1]
                    //var result = one.toDouble()-two.toDouble()
                    //tvinput?.text = result.toString()
                    if(prefix.isNotEmpty()){                                        //Doubt
                        one = prefix+one                                             //Doubt
                    }                                                                   //DOubt
                    tvinput?.text = removeZeroAfterDot((one.toDouble()/two.toDouble()).toString())
                }

                
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot (result: String):String{
        var value=result
        if(result.contains(".0"))
            value=result.substring(startIndex = 0, result.length-2)
        return value
    }

    private fun isOperatorAdded(value:String) :Boolean{
        return if(value.startsWith(prefix = "-")) {
            false
        } else{
            value.contains(other = "/")
                    || value.contains(other = "+")
                    || value.contains(other = "*")
                    || value.contains(other = "-")
        }
    }
}