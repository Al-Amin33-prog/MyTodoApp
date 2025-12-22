package com.example.myapplication1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication1.ui.theme.Lexend
import com.example.myapplication1.ui.theme.MyApplication1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  LoginScreen(
    onSkip: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text("Welcome Back",
                    fontFamily = Lexend,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge)},
                actions = {
                    TextButton(onClick = onSkip) {
                        Text("Skip For Now")
                    }
                }
            )
        }
    ) {padding ->
        Column(
            modifier = Modifier
                .background(Color(0xFFE8F5E9))
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_todologo),
                "Logo",
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = {Text("Email")},
                leadingIcon = {R.drawable.ic_password_lock },
                modifier = Modifier
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = "",
                {},
                label = {Text("Password")},
                leadingIcon = {R.drawable.ic_password_lock},
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }


        }

    }



}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MyApplication1Theme {
        LoginScreen(
            onSkip = {}
        )
    }
}
