package com.news.newsapi.util


import com.news.newsapi.data.news.News
import com.news.newsapi.data.sources.Sources

import java.text.SimpleDateFormat

val testNewsA =
    News(
        sourceId = "rte",
        author= "RTÉ Sport",
        title= "Pep turns an eye towards Real after stinging Hornets",
        description= "Pep Guardiola was pleased to see Manchester City take a step towards their best form with a 4-0 win over Watford ahead of next month's Champions League tie with Real Madrid.",
        url= "https://www.rte.ie/sport/soccer/2020/0721/1154743-pep-turns-an-eye-towards-real-after-stinging-hornets/",
        urlToImage= "https://img.rasset.ie/0014da3a-1600.jpg",
        publishedAt= SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("2020-07-21 20:39:48Z"),
        content= "Pep Guardiola was pleased to see Manchester City take a step towards their best form with a 4-0 win over Watford ahead of next month's Champions League tie with Real Madrid.\r\nA first-half brace from … [+2127 chars]"

)

val testNewsB =
    News(
        sourceId = "rte",
        author= "RTtt Sport",
        title= "pppp turns an eye towards Real after stinging Hornets",
        description= "ppp Guardiola was pleased to see Manchester City take a step towards their best form with a 4-0 win over Watford ahead of next month's Champions League tie with Real Madrid.",
        url= "https://www.rte.ie/sport/soccer/2020/0721/1154743-pep-turns-an-eye-towards-real-after-stinging-hornetspp/",
        urlToImage= "https://img.rasset.ie/pp0014da3a-1600.jpg",
        publishedAt= SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse("2020-07-21 20:39:49Z"),
        content= "ppp Guardiola was pleased to see Manchester City take a step towards their best form with a 4-0 win over Watford ahead of next month's Champions League tie with Real Madrid.\r\nA first-half brace from … [+2127 chars]"

    )

val testSourcesB =
    Sources(
        id= "rte",
        name= "RTE",
        sourceDescription= "Get all of the latest breaking local and international news stories as they happen, with up to the minute updates and analysis, from Ireland's National Broadcaster.",
        sourceUrl= "https://www.rte.ie/news",
        category= "general",
        language= "en",
        country= "ie"
    )


