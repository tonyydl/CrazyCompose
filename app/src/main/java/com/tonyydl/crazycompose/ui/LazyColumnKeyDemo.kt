package com.tonyydl.crazycompose.ui

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tonyydl.crazycompose.ui.theme.CrazyComposeTheme
import kotlinx.coroutines.launch

private const val TAG = "Try"

@Composable
private fun Try() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        A()
    }
}

@Preview
@Composable
private fun PreviewTry() {
    CrazyComposeTheme {
        Try()
    }
}

@Composable
private fun A() {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)
    var currentColorIndex by remember { mutableIntStateOf(0) }

    val color by animateColorAsState(
        targetValue = colors[currentColorIndex],
        label = "colorAnimation"
    )

//    val color = remember { Animatable(colors[currentColorIndex]) }
//    LaunchedEffect(currentColorIndex) {
//        color.animateTo(
//            targetValue = colors[currentColorIndex],
//            animationSpec = tween(durationMillis = 500)
//        )
//    }

//    val transition = updateTransition(targetState = currentColorIndex, label = "colorTransition")
//
//    val color by transition.animateColor(
//        transitionSpec = { tween(durationMillis = 500) }, label = "colorAnimation"
//    ) { state ->
//        colors[state]
//    }

    Box(
        modifier = Modifier
            .shadow(elevation = 2.dp, shape = RoundedCornerShape(8.dp))
            .clip(RoundedCornerShape(8.dp))
            .border(1.dp, Color.Gray)
            .background(color)
            .padding(16.dp)
            .clickable {
                currentColorIndex = (currentColorIndex + 1) % colors.size
            },
        contentAlignment = Alignment.Center
    ) {
        Text("LFG", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Bold)
    }

}

@Preview
@Composable
fun PreviewA() {
    CrazyComposeTheme {
        A()
    }
}

@Composable
fun C(modifier: Modifier = Modifier) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
//    val blur by animateDpAsState(targetValue = if (isPressed) 4.dp else 10.dp, label = "")

    Box(
        modifier
            .shadow(5.dp)
//            .blur(blur, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            .background(Color.Black, CircleShape)
            .clickable(
                interactionSource = interactionSource,
                indication = rememberRipple()
            ) {}
    )
}

@Preview
@Composable
private fun PreviewC() {
    CrazyComposeTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            C(modifier = Modifier.size(250.dp))
        }
    }
}

@Composable
fun D(modifier: Modifier = Modifier) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(state = listState) {
        items(100) { index ->
            Text("Item #$index")
        }
    }

    Button(onClick = {
        coroutineScope.launch {
            listState.animateScrollToItem(index = 50)
        }
    }) {
        Text("Scroll to item 50")
    }
}

@Preview
@Composable
private fun PreviewD() {
    CrazyComposeTheme {
        D()
    }
}

@Composable
fun AnimateScrollToExample() {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(0)
                    }
                }
            ) {
                Text("Scroll to Top")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(2000)
                    }
                }
            ) {
                Text("Scroll to 2000px")
            }
        }

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            repeat(50) { index ->
                Text(
                    "Item #$index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAnimateScrollToExample() {
    CrazyComposeTheme {
        AnimateScrollToExample()
    }
}

@Composable
fun LazyColumnScrollToItemExample() {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                }
            ) {
                Text("Scroll to Top")
            }

            Button(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(50)
                    }
                }
            ) {
                Text("Scroll to Item 50")
            }
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(100) { index ->
                Text(
                    "Item #$index",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLazyColumnScrollToItemExample() {
    CrazyComposeTheme {
        LazyColumnScrollToItemExample()
    }
}

@Composable
fun E() {
    var text by remember { mutableStateOf("") }

    Row {
        Button(
            onClick = {
                Log.d(TAG, "beat! text=$text")
            }
        ) {
            Text("Click me")
        }

        TextField(
            value = text,
            onValueChange = { text = it }
        )
    }
}

@Preview
@Composable
private fun PreviewE() {
    E()
}

@Composable
fun ListCA(myList: List<String>, onClick: () -> Unit) {
    Row(Modifier.clickable(onClick = onClick)) {
        Column {
            for (item in myList) {
                Text("Item: $item")
            }
        }
        Text("Count: ${myList.size}")
    }
}

@Preview
@Composable
private fun PreviewListWithBug() {
    var items by remember { mutableStateOf(listOf("A", "B", "C")) }
    ListCA(items) {
        items += "1"
    }
}

@Composable
private fun NamePicker(
    header: String,
    names: List<String>,
    onNameClicked: (String) -> Unit
) {
    Column {
        Text(header, style = MaterialTheme.typography.headlineMedium)
        HorizontalDivider()
        LazyColumn {
            items(names) { name ->
                NamePickerItem(name, onNameClicked)
            }
        }
    }
}

@Composable
private fun NamePickerItem(name: String, onClicked: (String) -> Unit) {
    Log.d(TAG, "NamePickerItem")
    Text(name, modifier = Modifier.clickable { onClicked(name) })
}

@Preview
@Composable
private fun PreviewNamePickerItem() {
    val header = "Header"
    var names by remember { mutableStateOf(listOf("Tony", "James", "Wade")) }
    var count by remember { mutableIntStateOf(0) }
    NamePicker(header, names) { name ->
        Log.d(TAG, "name:$name")
        val letter = name.filter { it.isLetter() }
        Log.d(TAG, "letter:$letter")
        count++
        names += "${letter}${count}"
    }
}

data class Content(
    val id: Int,
    val msg: String
)

@Composable
private fun HelloCompose(contents: List<Content>, onClick: (Content) -> Unit) {
    Column {
        for (content in contents) {
            key(content.id) {
                Log.d(TAG, "content.msg=${content}")
                Text(content.msg, modifier = Modifier.clickable {
                    onClick(content)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHelloCompose() {
    var contents by remember {
        mutableStateOf(
            listOf(
                Content(1, "Tony"),
                Content(2, "Lebron"),
                Content(3, "Bronny")
            )
        )
    }

    val clickCounts = remember { mutableStateMapOf<Int, Int>() }

    HelloCompose(contents) { clickedContent ->
        val currentCount = clickCounts.getOrDefault(clickedContent.id, 0) + 1
        clickCounts[clickedContent.id] = currentCount

        contents = contents.map { content ->
            if (content.id == clickedContent.id) {
                val newMsg = if (currentCount > 0) {
                    "${content.msg.split(" ")[0]}$currentCount"
                } else {
                    content.msg
                }
                content.copy(msg = newMsg)
            } else {
                content
            }
        }
    }
}