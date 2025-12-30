package com.example.myapplication1.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import com.example.Todo.R
import com.example.Todo.ui.theme.Lexend
import com.example.Todo.ui.theme.MyApplication1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  LoginScreen(
    onSkip: () -> Unit
){
    Scaffold(
        containerColor = Color(0xFFE8F5E9),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Welcome Back",
                        fontFamily = Lexend,
                        fontWeight = FontWeight.Companion.ExtraBold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                actions = {
                    TextButton(onClick = onSkip) {
                        Text("Skip For Now", fontWeight = FontWeight.Bold)
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.Companion
                .background(Color(0xFFE8F5E9))
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.ic_todologo),
                "Logo",
                modifier = Modifier.Companion.size(100.dp)
            )
            Spacer(modifier = Modifier.Companion.height(32.dp))
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {Text("Type in your Email")},
                label = { Text("Email") },
                leadingIcon = { R.drawable.ic_password_lock },
                modifier = Modifier.Companion
                    .fillMaxWidth()

            )
            Spacer(modifier = Modifier.Companion.height(12.dp))
            OutlinedTextField(
                value = "",
                {},
                placeholder = {Text("Type in your Password")},
                label = { Text("Password") },
                leadingIcon = { R.drawable.ic_password_lock },
                modifier = Modifier.Companion.fillMaxWidth()

            )
            Spacer(modifier = Modifier.Companion.height(24.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
               Text("Forgot Password?",fontFamily= Lexend, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.Companion.height(24.dp))
            Button(
                onClick = {},
                modifier = Modifier.Companion.fillMaxWidth()
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