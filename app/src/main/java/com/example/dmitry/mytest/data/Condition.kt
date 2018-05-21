package com.example.dmitry.mytest.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Condition(
		@SerializedName("code") @Expose val code: String,
		@SerializedName("date") @Expose val date: String,
		@SerializedName("temp") @Expose val temp: String,
		@SerializedName("text") @Expose val text: String
) {
	companion object {
		const val errorMessage = "Error"
	}
	constructor(message: String) : this(
			message,
			message,
			message,
			message)

	constructor() : this(
			errorMessage,
			errorMessage,
			errorMessage,
			errorMessage)
}