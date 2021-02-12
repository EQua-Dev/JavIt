package com.androidstrike.javit.auth

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.androidstrike.cofepa.utils.login
import com.androidstrike.cofepa.utils.toast
import com.androidstrike.javit.R
import com.androidstrike.javit.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUp : Fragment() {

    //    a firebase auth object is created to enable us create a user in the firebase console
//    we have the lateinit var called mAuth where we store the instance of the FirebaseAuth
    private lateinit var mAuth : FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var table_user: DatabaseReference

    private var userId:String?=null
    private var emailAddress:String?=null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        here we initialize the instance of the Firebase Auth
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        table_user = database.getReference("Users")

        button_sign_up.setOnClickListener {
            val email = et_new_email.text.toString().trim()
            val password = et_new_password.text.toString().trim()
            val confirm_password = et_new_confirm_password.text.toString().trim()


//            if the email and password fields are empty we display error messages
            if (email.isEmpty()){
                et_new_email.error = "Email Required"
                et_new_email.requestFocus()
                return@setOnClickListener
            }

//            if the email pattern/format does not does match that as defined, we display error messages
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                et_new_email.error = "Valid Email Required"
                et_new_email.requestFocus()
                return@setOnClickListener
            }

//            if the password contains less than 6 characters we display error message
            if (password.isEmpty() || password.length < 6){
                et_new_password.error = "6 char password required"
                et_new_password.requestFocus()
                return@setOnClickListener
            }
            if (confirm_password != password) {
                et_new_confirm_password.error = "Must Match With Password"
            }else{

                registerUser(email, password)

            }

        }

        tv_login_instead.setOnClickListener {
            val fragment = SignIn()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.auth_frame, fragment, fragment.javaClass.simpleName)
                ?.commit()
        }
    }


    //    in this method we can create a user in firebase console
    private fun registerUser(email: String, password: String) {
        pb_sign_up.visibility = View.VISIBLE

//    using the firebase object instance we create a user in the firebase console

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    //Registration success
                    //we call the login function from the helper class
                    //todo add the other details to be saved in the realtime database
                    val user = mAuth.currentUser
                    userId = user!!.uid
                    emailAddress = user.email

                    val newUser = User(et_new_user_name.text.toString(),emailAddress.toString(),et_new_phone_number.text.toString())
                    table_user.child(userId!!).setValue(newUser)
//                    Common.student_name = et_new_user_name.text.toString()
//                    Common.student_department = spinner_text.toString()
                    activity?.pb_sign_up?.visibility = View.GONE
                    activity?.login()
                }else{
//                we show the error message from the attempted registration
//                we set the exception message to be non nullable
                    it.exception?.message?.let {
                        activity?.pb_sign_up?.visibility = View.GONE

//                    we call the toast function from the helper class
                        activity?.toast(it)
                    }
                }
            }

    }

//    override fun onStart() {
////        super.onStart()
//        // if the current user is already signed in onStart of the application, the login function is called
//        mAuth.currentUser?.let {
//            activity?.login()
//        }
//    }
}