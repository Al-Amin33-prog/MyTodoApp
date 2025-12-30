package com.example.Todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication1.ui.navigator.TaskNavigator
import com.example.Todo.ui.theme.MyApplication1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplication1Theme {
                TaskNavigator()
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetAppPreview() {
    MyApplication1Theme {

    }
}
