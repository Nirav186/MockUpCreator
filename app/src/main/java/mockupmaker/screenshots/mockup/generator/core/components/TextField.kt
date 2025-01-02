package mockupmaker.screenshots.mockup.generator.core.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import mockupmaker.screenshots.mockup.generator.ui.theme.CustomTextFieldStyle

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textFieldTextStyle: TextStyle = CustomTextFieldStyle,
    placeholderText: String
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholderText,
                color = Color.Gray,
                style = textFieldTextStyle
            )
        }, colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = textFieldTextStyle,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }
        )
    )
}

@Preview
@Composable
private fun PreviewCustomTextField() {
    CustomTextField(
        value = "aa",
        onValueChange = {},
        placeholderText = "a"
    )
}