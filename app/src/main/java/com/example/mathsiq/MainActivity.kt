package com.example.mathsiq

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mathsiq.ui.theme.MathsIQTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private fun exitApp(){
        finish()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MathsIQTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Controller { exitApp() }
                }
            }
        }
    }
}
@Composable
fun Buttons(
    text: String,
    fn : () -> Unit
) {
    ElevatedButton(
        onClick = { fn() },
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 8.dp
        ),
        modifier = Modifier
            .width(300.dp)
            .padding(vertical = 3.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = Color.White,
            containerColor = Color(48, 48, 49)
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Composable
fun ButtonPage2(
    text: String,
    fn : () -> Unit,
    color: Color
) {
    ElevatedButton(
        onClick = { fn() },
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 8.dp
        ),
        modifier = Modifier
            .width(300.dp)
            .padding(vertical = 10.dp),
        colors = ButtonDefaults.elevatedButtonColors(
            contentColor = Color.Black,
            containerColor = color
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Heading(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color(146, 146, 146),
                        Color(105, 105, 105)
                    )
                )
            )
            .padding(vertical = 10.dp),
        textAlign = TextAlign.Center,
        color = Color.Cyan,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.displaySmall
    )
}

@Composable
fun CreateCard(
    string: String,
    computer: String,
    fn: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .height(260.dp)
            .background(Color.Transparent),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.DarkGray,
                            Color.LightGray, Color.Gray
                        )
                    )
                )
        ) {
            Text(
                text = string,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 20.dp),
                color = Color.Red
            )
            Text(
                text = computer,
                style = MaterialTheme.typography.headlineLarge,
                color = Color.Red
            )
            ButtonPage2(
                text = "Start Game", fn = { fn() },
                color = Color(116, 203, 231))
        }
    }
}

// Initialization
@Composable
fun InitComponent(
    navController: NavController, fn: () -> Unit
) {
    var status by remember {
        mutableStateOf(false)
    }
    if (status){
        AlertDialog(
            onDismissRequest = { status = false },
            title = { Text(text = "To be Added")},
            text = { Text(text = """
                Sorry for the inconvenience this feature
                will be add in the next version
            """.trimIndent())},
            confirmButton = {
                TextButton(onClick = {
                    status = false
                }) {
                    Text(text = "Ok")
                }
            }
        )
    }
    Column {
        Heading(text = "Maths IQ")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.theme2),
                contentDescription = "Mental maths",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.alpha(8.5f)
            )
            Column {
                Buttons(text = "Guess Number", fn = {
                    navController.navigate("levelPage/game1/Guess Number")
                })
                Buttons(text = "Memory Test", fn = {
                    navController.navigate("levelPage/game2/Memory Test")
                })
                Buttons(text = "Random Addition", fn = {
                    navController.navigate("levelPage/game3/Addition")
                })
                Buttons(text = "Random Multiply", fn = {
                    navController.navigate("levelPage/game4/Multiplication")
                })
                Buttons(text = "Random Subtract", fn = {
                    navController.navigate("levelPage/game5/Subtraction")
                })
                Buttons(text = "Setting", fn = {
                    status = true
                })
                Buttons(text = "Exit App", fn = {
                    fn()
                })
            }
        }
    }
}

@Composable
fun SelectLevel(navController: NavController, name: String, value: String) {
    var status by remember {
        mutableStateOf(false)
    }
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }


    fun checker(level: String){
        when (name) {
            "game1" -> navController.navigate(route = "game1/$level")
            "game2" -> navController.navigate(route = "game2/$level")
            "game3" -> navController.navigate(route = "game3/$level")
            "game4" -> navController.navigate(route = "game4/$level")
            "game5" -> navController.navigate(route = "game5/$level")
        }
    }
    if (status){
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text = title)},
            text = { Text(text = description)},
            confirmButton = {
                TextButton(onClick = {
                }) {
                    Text(text = "Game Rules")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    status = false
                    navController.navigate("frontpage")
                }) {
                    Text(text = "Home")
                }
            }
        )
    }
    val scope = rememberCoroutineScope()

    Column {
        Heading(text = value)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.theme),
                contentDescription = "Mental maths",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.alpha(8.5f)
            )
            Column {
                ButtonPage2(text = "Easy", fn = {
                    scope.launch {
                        title = "You Choose Easy Level"
                        when(name){
                            "game1" -> {
                                description = """
                                    Computer Will Think A number from 0 to 15
                                    You have to guess the number correctly
                                    in 6 chances
                                """.trimIndent()
                            }
                            "game2" -> {
                                description = """
                                    You will see 3 numbers between 0 to 50 randomly
                                    with a delay of 2 seconds
                                    You have to write the Sequence of the
                                    number as show by the computer
                                """.trimIndent()
                            }
                            "game3" -> {
                                description = """
                                    You will see 3 numbers between 0 to 30 randomly
                                    with a delay of 2 second
                                    You have to Provide the Sum of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                            "game4" -> {
                                description = """
                                    You will see 3 numbers between 1 to 10 randomly
                                    with a delay of 2 second
                                    You have to Provide the Product of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                            "game5" -> {
                                description = """
                                    You will see 3 numbers between 0 to 20 randomly
                                    with a delay of 2 second
                                    You have to Provide the Subtraction of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                        }
                        status = true
                        delay(3000)
                        checker("easy")
                    }
                }, Color.Cyan)
                ButtonPage2(text = "Medium", fn = {
                    scope.launch {
                        title = "You Choose Medium Level"
                        when(name){
                            "game1" -> {
                                description = """
                                    Computer Will Think A number from 1 to 20
                                    You have to guess the number correctly
                                    in 5 chances
                                """.trimIndent()
                            }
                            "game2" -> {
                                description = """
                                    You will see 4 numbers between 1 to 100 randomly
                                    with a delay of 1.5 seconds
                                    You have to write the Sequence of the
                                    number as show by the computer
                                """.trimIndent()
                            }
                            "game3" -> {
                                description = """
                                    You will see 4 numbers between 1 to 60 randomly
                                    with a delay of 1.5 second
                                    You have to Provide the Sum of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                            "game4" -> {
                                description = """
                                    You will see 4 numbers between 1 to 15 randomly
                                    with a delay of 2 second
                                    You have to Provide the Product of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                            "game5" -> {
                                description = """
                                    You will see 4 numbers between 1 to 30 randomly
                                    with a delay of 1.5 second
                                    You have to Provide the Subtraction of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                        }
                        status = true
                        delay(3000)
                        checker("medium")
                    }
                }, Color.Yellow)
                ButtonPage2(text = "Hard", fn = {
                    scope.launch {
                        title = "You Choose Hard Level"
                        when(name){
                            "game1" -> {
                                description = """
                                    Computer Will Guess A number from 1 to 30
                                    You have to guess the number correctly
                                    in 4 chances
                                """.trimIndent()
                            }
                            "game2" -> {
                                description = """
                                    You will see 5 numbers between 1 to 300 randomly
                                    with a delay of 1.5 second
                                    You have to write the Sequence of the
                                    number as show by the computer
                                """.trimIndent()
                            }
                            "game3" -> {
                                description = """
                                    You will see 5 numbers between 1 to 100 randomly
                                    with a delay of 1.5 second
                                    You have to Provide the Sum of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                            "game4" -> {
                                description = """
                                    You will see 5 numbers between 1 to 20 randomly
                                    with a delay of 2 second
                                    You have to Provide the Product of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                            "game5" -> {
                                description = """
                                    You will see 5 numbers between 1 to 50 randomly
                                    with a delay of 1.5 second
                                    You have to Provide the Subtraction of All the numbers
                                    shown by the Computer
                                """.trimIndent()
                            }
                        }
                        status = true
                        delay(3000)
                        checker("hard")
                    }
                }, Color.Red)
            }
        }
    }
}

// Full Navigation
@Composable
fun Controller(fn: () -> Unit){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "frontpage") {
        composable(route = "frontpage"){
            InitComponent(navController, fn = {fn()})
        }
        composable(route = "levelPage/{name}/{value}"){
            SelectLevel(navController,
                it.arguments?.getString("name") ?: "",
                it.arguments?.getString("value") ?: ""
            )
        }
        composable(route = "game1/{level}"){
            GuessNumber(
                navController, it.arguments?.getString("level") ?: ""
            )
        }
        composable(route = "game2/{level}"){
            MemoryTest(navController, it.arguments?.getString("level") ?: "")
        }
        composable(route = "game3/{level}"){
            Addition(navController, it.arguments?.getString("level") ?: "")
        }
        composable(route = "game4/{level}"){
            Multiplication(navController, it.arguments?.getString("level") ?: "")
        }
        composable(route = "game5/{level}"){
            Subtraction(navController, it.arguments?.getString("level") ?: "")
        }
    }
}


// Games
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumber(navController: NavController, level: String) {
    var dis by remember {
        mutableStateOf("Chances")
    }
    var des by remember {
        mutableStateOf("")
    }
    var computer by remember {
        mutableIntStateOf(0)
    }
    var count by remember {
        mutableIntStateOf(0)
    }
    var answer by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf(false)
    }
    var selection by remember {
        mutableStateOf("Click Button to Start")
    }
    var playing by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    fun reInit(){
        dis = "Chances"
        computer = 0
        count = 0
        answer = ""
        selection = "Click Button to Start"
    }
    fun checkAnswer(){
        count --
        dis = "Remaining $count"
        if(computer == answer.toInt()){
            des = "You won the Game\nCongratulations"
            status = true
        }
        else if(computer > answer.toInt()){
            des = "Value is greater\nmove up"
        }
        else if(computer > answer.toInt()){
            des = "Value is lower\nmove down"
        }
        else if (count == 0){
            des = "You Lose the Game!!!\nComputer's Choice Was $computer"
            status = true
        }
    }
    fun play(){
        reInit()
        playing = true
        when(level) {
            "easy" -> {
                count = 6
                dis = "Remaining $count"
                computer = Random.nextInt(0, 15)
                selection = "Guess Number[0-15]"
            }
            "medium" -> {
                count = 5
                computer = Random.nextInt(0, 20)
                dis = "Remaining $count"
                selection = "Guess Number[0-20]"
            }
            "hard" -> {
                count = 4
                computer = Random.nextInt(0, 30)
                dis = "Remaining $count"
                selection = "Guess Number[0-300]"
            }
        }
    }
    if (status){
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text = "Game Result")},
            text = { Text(text = des)},
            confirmButton = {
                TextButton(onClick = {
                    status = false
                    scope.launch {
                        reInit()
                        delay(1000)
                        play()
                    }
                }) {
                    Text(text = "Play again")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    status = false
                    reInit()
                    navController.navigate("frontpage")
                }) {
                    Text(text = "Home")
                }
            }
        )
    }
    Column {
        Heading(text = "Guess Number")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.guess),
                contentDescription = "Mental maths",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.alpha(8.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                IconButton(
                    onClick = {
                        navController.navigate("frontpage")
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Previous",
                        modifier = Modifier.size(100.dp),
                        tint = Color.Red
                    )
                }
                CreateCard(string = dis, computer = selection, fn = {play()})
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 1.dp)
                        .border(BorderStroke(1.dp, Color.Green))
                        .background(Color.Transparent),
                    placeholder = {
                        Text(
                            text = "Your answer here",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.LightGray
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            when(answer){
                                "" ->{}
                                else -> {
                                    when(playing){
                                        false -> {}
                                        true -> checkAnswer()
                                    }
                                }
                            }
                        }
                    )
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoryTest(navController: NavController, level: String) {
    var computerList by remember {
        mutableStateOf(mutableListOf<String>())
    }
    var des by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf(false)
    }
    var selection by remember {
        mutableStateOf("Click Button to Start")
    }
    var playing by remember {
        mutableStateOf(false)
    }
    var field by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    fun reInit(){
        answer = ""
        selection = "Click Button to Start"
        field = false
        computerList = mutableListOf()
    }
    fun checkAnswer(){
        val userList = answer.split(",")
        if(computerList == userList){
            des = "You Won the game!!!"
            status = true
        }
        else{
            des = "You Lost!!!\nTry Again"
            status = true
        }
    }
    fun play(){
        reInit()
        playing = true
        when(level) {
            "easy" -> {
                scope.launch {
                    repeat(3){
                        selection = Random.nextInt(0, 50).toString()
                        computerList.add(selection)
                        delay(2000)
                    }
                    selection = "Correct Sequence"
                    field = true
                }

            }
            "medium" -> {
                scope.launch {
                    repeat(4){
                        selection = Random.nextInt(1, 100).toString()
                        computerList.add(selection)
                        delay(1500)
                    }
                    selection = "Correct Sequence"
                    field = true
                }
            }
            "hard" -> {
                scope.launch {
                    repeat(5){
                        selection = Random.nextInt(1, 200).toString()
                        computerList.add(selection)
                        delay(1500)
                    }
                    selection = "Correct Sequence"
                    field = true
                }
            }
        }
    }
    if (status){
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text = "Game Result") },
            text = { Text(text = des) },
            confirmButton = {
                TextButton(onClick = {
                    status = false
                    scope.launch {
                        reInit()
                        delay(1000)
                        play()
                    }
                }) {
                    Text(text = "Play again")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    status = false
                    reInit()
                    navController.navigate("frontpage")
                }) {
                    Text(text = "Home")
                }
            }
        )
    }
    Column {
        Heading(text = "Memory Test")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.guess),
                contentDescription = "Mental maths",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.alpha(8.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                IconButton(
                    onClick = {
                        navController.navigate("frontpage")
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Previous",
                        modifier = Modifier.size(100.dp),
                        tint = Color.Red
                    )
                }
                CreateCard(string = "Remember Sequence", computer = selection, fn = {play()})
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 1.dp)
                        .border(BorderStroke(1.dp, Color.Green))
                        .background(Color.Transparent),
                    placeholder = {
                        Text(
                            text = "Your answer here",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.LightGray
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    enabled = field,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            when(answer){
                                "" ->{}
                                else -> {
                                    when(playing){
                                        false -> {}
                                        true -> checkAnswer()
                                    }
                                }
                            }
                        }
                    )
                )
            }
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Addition(navController: NavController, level: String) {
    var des by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf(false)
    }
    var computer by remember {
        mutableIntStateOf(0)
    }
    var selection by remember {
        mutableStateOf("Click Button to Start")
    }
    var playing by remember {
        mutableStateOf(false)
    }
    var field by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    fun reInit(){
        computer = 0
        answer = ""
        selection = "Click Button to Start"
        field = false
    }
    fun checkAnswer(){
        if(computer == answer.toInt()){
            des = "You Won the game!!!"
            status = true
        }
        else{
            des = "You Lost!!!\nTry Again"
            status = true
        }
    }
    fun play(){
        reInit()
        playing = true
        when(level) {
            "easy" -> {
                scope.launch {
                    repeat(3){
                        selection = Random.nextInt(0, 30).toString()
                        computer+=selection.toInt()
                        delay(2000)
                    }
                    selection = "Correct Sum"
                    field = true
                }

            }
            "medium" -> {
                scope.launch {
                    repeat(5){
                        selection = Random.nextInt(1, 60).toString()
                        computer+=selection.toInt()
                        delay(1500)
                    }
                    selection = "Correct Sum"
                    field = true
                }
            }
            "hard" -> {
                scope.launch {
                    repeat(5){
                        selection = Random.nextInt(1, 100).toString()
                        computer+=selection.toInt()
                        delay(1500)
                    }
                    selection = "Correct Sum"
                    field = true
                }
            }
        }
    }
    if (status){
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text = "Game Result") },
            text = { Text(text = des) },
            confirmButton = {
                TextButton(onClick = {
                    status = false
                    scope.launch {
                        reInit()
                        delay(1000)
                        play()
                    }
                }) {
                    Text(text = "Play again")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    status = false
                    reInit()
                    navController.navigate("frontpage")
                }) {
                    Text(text = "Home")
                }
            }
        )
    }
    Column {
        Heading(text = "Addition")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.guess),
                contentDescription = "Mental maths",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.alpha(8.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                IconButton(
                    onClick = {
                        navController.navigate("frontpage")
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Previous",
                        modifier = Modifier.size(100.dp),
                        tint = Color.Red
                    )
                }
                CreateCard(string = "Remember Total", computer = selection, fn = {play()})
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 1.dp)
                        .border(BorderStroke(1.dp, Color.Green))
                        .background(Color.Transparent),
                    placeholder = {
                        Text(
                            text = "Your answer here",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.LightGray
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    enabled = field,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            when(answer){
                                "" ->{}
                                else -> {
                                    when(playing){
                                        false -> {}
                                        true -> checkAnswer()
                                    }
                                }
                            }
                        }
                    )
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Multiplication(navController: NavController, level: String) {
    var des by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf(false)
    }
    var computer by remember {
        mutableIntStateOf(1)
    }
    var selection by remember {
        mutableStateOf("Click Button to Start")
    }
    var playing by remember {
        mutableStateOf(false)
    }
    var field by remember {
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    fun reInit(){
        computer = 1
        answer = ""
        selection = "Click Button to Start"
        field = false
    }
    fun checkAnswer(){
        if(computer == answer.toInt()){
            des = "You Won the game!!!"
            status = true
        }
        else{
            des = "You Lost!!!\nTry Again"
            status = true
        }
    }
    fun play(){
        reInit()
        playing = true
        when(level) {
            "easy" -> {
                scope.launch {
                    repeat(3){
                        selection = Random.nextInt(1, 10).toString()
                        computer*=selection.toInt()
                        delay(2000)
                    }
                    selection = "Correct Product"
                    field = true
                }

            }
            "medium" -> {
                scope.launch {
                    repeat(4){
                        selection = Random.nextInt(1, 15).toString()
                        computer*=selection.toInt()
                        delay(2000)
                    }
                    selection = "Correct Product"
                    field = true
                }
            }
            "hard" -> {
                scope.launch {
                    repeat(5){
                        selection = Random.nextInt(1, 20).toString()
                        computer*=selection.toInt()
                        delay(2000)
                    }
                    selection = "Correct Product"
                    field = true
                }
            }
        }
    }
    if (status){
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text = "Game Result") },
            text = { Text(text = des) },
            confirmButton = {
                TextButton(onClick = {
                    status = false
                    scope.launch {
                        reInit()
                        delay(1000)
                        play()
                    }
                }) {
                    Text(text = "Play again")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    status = false
                    reInit()
                    navController.navigate("frontpage")
                }) {
                    Text(text = "Home")
                }
            }
        )
    }
    Column {
        Heading(text = "Multiplication")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.guess),
                contentDescription = "Mental maths",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.alpha(8.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                IconButton(
                    onClick = {
                        navController.navigate("frontpage")
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Previous",
                        modifier = Modifier.size(100.dp),
                        tint = Color.Red
                    )
                }
                CreateCard(string = "Remember Total", computer = selection, fn = {play()})
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 1.dp)
                        .border(BorderStroke(1.dp, Color.Green))
                        .background(Color.Transparent),
                    placeholder = {
                        Text(
                            text = "Your answer here",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.LightGray
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    enabled = field,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            when(answer){
                                "" ->{}
                                else -> {
                                    when(playing){
                                        false -> {}
                                        true -> checkAnswer()
                                    }
                                }
                            }
                        }
                    )
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Subtraction(navController: NavController, level: String) {
    var des by remember {
        mutableStateOf("")
    }
    var answer by remember {
        mutableStateOf("")
    }
    var status by remember {
        mutableStateOf(false)
    }
    var computer by remember {
        mutableIntStateOf(0)
    }
    var selection by remember {
        mutableStateOf("Click Button to Start")
    }
    var playing by remember {
        mutableStateOf(false)
    }
    var field by remember {
        mutableStateOf(false)
    }
    var list by remember {
        mutableStateOf(mutableListOf<Int>())
    }
    val scope = rememberCoroutineScope()
    fun reInit(){
        list = mutableListOf()
        computer = 0
        answer = ""
        selection = "Click Button to Start"
        field = false
    }
    fun checkAnswer(){
        if(computer == answer.toInt()){
            des = "You Won the game!!!"
            status = true
        }
        else{
            des = "You Lost!!!\nTry Again"
            status = true
        }
    }
    fun play(){
        reInit()
        playing = true
        when(level) {
            "easy" -> {
                scope.launch {
                    repeat(3){
                        selection = Random.nextInt(0, 20).toString()
                        list.add(selection.toInt())
                        delay(2000)
                    }
                    computer = list[0]
                    for (i in list.indices){
                        if(i == 0){
                            continue
                        }
                        else{
                            computer -= list[i]
                        }
                    }
                    selection = "Correct Sum"
                    field = true
                }

            }
            "medium" -> {
                scope.launch {
                    repeat(4){
                        selection = Random.nextInt(1, 30).toString()
                        list.add(selection.toInt())
                        delay(1500)
                    }
                    computer = list[0]
                    for (i in list.indices){
                        if(i == 0){
                            continue
                        }
                        else{
                            computer -= list[i]
                        }
                    }
                    selection = "Correct Sum"
                    field = true
                }
            }
            "hard" -> {
                scope.launch {
                    repeat(5){
                        selection = Random.nextInt(1, 50).toString()
                        computer-=selection.toInt()
                        delay(1500)
                    }
                    selection = "Correct Sum"
                    field = true
                }
            }
        }
    }
    if (status){
        AlertDialog(
            onDismissRequest = {  },
            title = { Text(text = "Game Result") },
            text = { Text(text = des) },
            confirmButton = {
                TextButton(onClick = {
                    status = false
                    scope.launch {
                        reInit()
                        delay(1000)
                        play()
                    }
                }) {
                    Text(text = "Play again")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    status = false
                    reInit()
                    navController.navigate("frontpage")
                }) {
                    Text(text = "Home")
                }
            }
        )
    }
    Column {
        Heading(text = "Subtraction")
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ){
            Image(
                painter = painterResource(id = R.drawable.guess),
                contentDescription = "Mental maths",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.alpha(8.5f)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                IconButton(
                    onClick = {
                        navController.navigate("frontpage")
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 18.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Previous",
                        modifier = Modifier.size(100.dp),
                        tint = Color.Red
                    )
                }
                CreateCard(string = "Remember Total", computer = selection, fn = {play()})
                OutlinedTextField(
                    value = answer,
                    onValueChange = { answer = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 1.dp)
                        .border(BorderStroke(1.dp, Color.Green))
                        .background(Color.Transparent),
                    placeholder = {
                        Text(
                            text = "Your answer here",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.LightGray
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    enabled = field,
                    keyboardActions = KeyboardActions(
                        onDone = {
                            when(answer){
                                "" ->{}
                                else -> {
                                    when(playing){
                                        false -> {}
                                        true -> checkAnswer()
                                    }
                                }
                            }
                        }
                    )
                )
            }
        }
    }
}
