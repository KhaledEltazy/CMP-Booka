package org.cmp.cmp_booka

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform