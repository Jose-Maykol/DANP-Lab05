package com.example.danp_lab05

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.AWSDataStorePlugin
import com.amplifyframework.datastore.generated.model.IoT
import com.example.danp_lab05.ui.theme.DANPLab05Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            Amplify.addPlugin(AWSApiPlugin()) // UNCOMMENT this line once backend is deployed
            Amplify.addPlugin(AWSDataStorePlugin())
            Amplify.configure(applicationContext)
            Log.i("Amplify", "Initialized Amplify")
        } catch (e: AmplifyException) {
            Log.e("Amplify", "Could not initialize Amplify", e)
        }
        super.onCreate(savedInstanceState)
        setContent {
            DANPLab05Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ///Greeting("Android")
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
    val item: IoT = IoT.builder()
        .datetime(Temporal.DateTime("1970-01-01T12:30:23.999Z"))
        .temperature(123.45)
        .humidity(123.45)
        .note("Lorem ipsum dolor sit amet")
        .build()
    Amplify.DataStore.save(
        item,
        { success -> Log.i("Amplify", "Saved item: " + success.item().note) },
        { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DANPLab05Theme {
        Greeting("Android")
    }
}