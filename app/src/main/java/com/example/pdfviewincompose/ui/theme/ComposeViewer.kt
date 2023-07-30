package com.example.pdfviewincompose.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pdfviewincompose.R


@ExperimentalFoundationApi
@Composable
fun ComposePDFViewer() {
    var isLoading by remember { mutableStateOf(false) }
    var currentLoadingPage by remember { mutableStateOf<Int?>(null) }
    var pageCount by remember { mutableStateOf<Int?>(null) }

    Box {
        PdfViewer(
            pdfResId = R.raw.ai,
            modifier = Modifier.fillMaxSize(),
            loadingListener = { loading, currentPage, maxPage ->
                isLoading = loading
                if (currentPage != null) currentLoadingPage = currentPage
                if (maxPage != null) pageCount = maxPage
            }
        )
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    progress = if (currentLoadingPage == null || pageCount == null) 0f
                    else currentLoadingPage!!.toFloat() / pageCount!!.toFloat()
                )

                Text(
                    text = "${currentLoadingPage ?: "-"} pages loaded / ${pageCount ?: "-"} total pages",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 5.dp)
                        .padding(horizontal = 30.dp)
                )
            }
        }
    }
}
