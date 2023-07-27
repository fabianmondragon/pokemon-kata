package com.example.data.utils

class StringUtil {

    companion object {
        fun getId(url: String): String {
            return url.split("/".toRegex()).dropLast(1).last()
        }
    }
}