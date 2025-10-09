package org.cmp.cmp_booka.book.presentation.book_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_booka.composeapp.generated.resources.Res
import cmp_booka.composeapp.generated.resources.favorites
import cmp_booka.composeapp.generated.resources.no_favorite_books
import cmp_booka.composeapp.generated.resources.no_search_results
import cmp_booka.composeapp.generated.resources.search_results
import org.cmp.cmp_booka.book.domain.Book
import org.cmp.cmp_booka.book.presentation.book_list.components.BookList
import org.cmp.cmp_booka.book.presentation.book_list.components.BookSearchBar
import org.cmp.cmp_booka.core.presentation.DarkBlue
import org.cmp.cmp_booka.core.presentation.DesertWhite
import org.cmp.cmp_booka.core.presentation.SandYellow
import org.jetbrains.compose.resources.stringResource
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun BookListScreenRoot(
    viewModel: BookListViewModel = getKoin().get(),
    onBookClick : (Book) -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    BookListScreen(
        state = state,
        onAction = { action ->
            when(action){
                is BookListAction.OnBookClick -> onBookClick(action.book)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun BookListScreen(
    state : BookListState,
    onAction : (BookListAction) -> Unit
){
    val keyboardController = LocalSoftwareKeyboardController.current

    val pagerState = rememberPagerState {2}
    val searchResultLazyListState = rememberLazyListState()
    val favoriteLazyListState = rememberLazyListState()

    LaunchedEffect(state.searchResults){
        searchResultLazyListState.animateScrollToItem(0)
    }
    //  change page when select tab or when swipe up
    LaunchedEffect(state.selectedTabIndex){
        pagerState.animateScrollToPage(state.selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage){
        onAction(BookListAction.OnTabSelected(pagerState.currentPage))
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(DarkBlue)
        .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BookSearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = {
                onAction(BookListAction.OnSearchQueryChange(it))
            },
            onImeSearch = {
                keyboardController?.hide()
            },
            modifier = Modifier.widthIn(min = 400.dp)
                .fillMaxWidth()
                .padding(16.dp)
        )

        Surface(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            color = DesertWhite,
            shape = RoundedCornerShape(
                topStart = 32.dp,
                topEnd = 32.dp
            )
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TabRow(
                    selectedTabIndex = state.selectedTabIndex,
                    modifier = Modifier
                        .padding(12.dp)
                        .widthIn(700.dp)
                        .fillMaxWidth(),
                    containerColor = DesertWhite,
                    contentColor = SandYellow,
                    indicator = { tabPosition ->
                        TabRowDefaults.SecondaryIndicator(
                            color = SandYellow,
                            modifier = Modifier
                                .tabIndicatorOffset(tabPosition[state.selectedTabIndex])
                        )
                    }
                ){
                    Tab(
                        selected = state.selectedTabIndex == 0,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = .5f)
                    ){
                        Text(
                            text = stringResource(Res.string.search_results),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                    Tab(
                        selected = state.selectedTabIndex == 1,
                        onClick = {
                            onAction(BookListAction.OnTabSelected(0))
                        },
                        modifier = Modifier.weight(1f),
                        selectedContentColor = SandYellow,
                        unselectedContentColor = Color.Black.copy(alpha = .5f)
                    ){
                        Text(
                            text = stringResource(Res.string.favorites),
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ){pageIndex ->
                    Box(
                        modifier = Modifier.
                        fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        when (pageIndex) {
                            0 -> {
                                if(state.isLoading){
                                    CircularProgressIndicator()
                                } else {
                                    when{
                                        state.errorMessage !=null ->{
                                            Text(
                                                text = state.errorMessage.asString(),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }
                                        state.searchResults.isEmpty() -> {
                                            Text(
                                                text = stringResource(Res.string.no_search_results),
                                                textAlign = TextAlign.Center,
                                                style = MaterialTheme.typography.headlineSmall,
                                                color = MaterialTheme.colorScheme.error
                                            )
                                        }
                                        else -> {
                                            BookList(
                                                books = state.searchResults,
                                                onBooKClick = {
                                                    onAction(BookListAction.OnBookClick(it))
                                                },
                                                modifier = Modifier.fillMaxSize(),
                                                scrollState = searchResultLazyListState
                                            )
                                        }
                                    }
                                }
                            }

                            1 -> {
                                if(state.favoriteBooks.isEmpty()){
                                    Text(
                                        text = stringResource(Res.string.no_favorite_books),
                                        textAlign = TextAlign.Center,
                                        style = MaterialTheme.typography.headlineSmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                } else {
                                    BookList(
                                        books = state.favoriteBooks,
                                        onBooKClick = {
                                            onAction(BookListAction.OnBookClick(it))
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                        scrollState = favoriteLazyListState
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

