package com.izakdvlpr.email.service.utils

import java.util.Properties

fun putIfMissing(props: Properties, key: String, value: String) {
  if (!props.containsKey(key)) {
    props[key] = value
  }
}