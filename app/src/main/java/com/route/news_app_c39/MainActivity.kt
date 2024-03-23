package com.route.news_app_c39

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.route.news_app_c39.databinding.ActivityMainBinding
import com.route.news_app_c39.newsSources.NewsSourcesFragment
// Week8
// API Application Programming Interface ( Web Service)
// make our app communicate with service to send to our app data or send to it a data
// Network communication between our App and Another App  (Web App)
// Client Server Communication
// Server ( provide service )(server hosted 3aleh database, database server ,web server , file server)
// response return with data and client ( request service ) request sent with parameter
// initiation from Client and after it communication not one way (two way)
// communication between client and server done by Http/s ( hyper text transfer protocol/ (SSL) secure socket layer )
// protocol is set of rules to communicate
// Request send with (parameters) ,Response return with (data)
// Requests types GET ,POST ,PUT ,PATCH ,DELETE ,HEAD ,OPTIONS
// 1.postman is App simulate calling od API
// 2.BackEnd developer make API documentation
// 3.testers load testing (try to send 1000 request in one second)
// DNS Domain Name Server i want speak with google.com give you IP .....
// solve Domain with IP (https://(WWW.google.com)/) after it api(search)?
// after ? parameters(q= & apiKey=& ie=UTF8) in GET Request (parameter of GET request send in url)
// Post Request cant work on Browsers need tool (Post parameters sent in url body)
// Request Header has parameters related to type of request and community of request ..ex:(token),
// RequestBody has parameters related to this request (id ,string you need in request)
// body has types as (form data ,form url coded ,raw,..)
// json java script object notation is( data format ) as ~=(CSB excel sheet)
// json as ~= XML but json is simpler
// json is (json object ,json array)
// object is (key (should be string) and value pairs) { "id":10, "Name":"Mohamed"} as map
// {} json object
// [] json array ,can be array of objects ["id":10,"age:30,"name":"yasser"]
// or array of primitive data type{"Courses":["Android","Ios","Flutter"]},courses is array of strings
////////////////////////////////////////////////////////////////////////////////////
// 90% API return data as (json) and in request sent parameters as (body(json)) ,
// request types (parameters send) as (  q , body(json),form data, form url coded)
// The order of the objects does not make different
// 200 success , 300 redirect ,400 client error response ,500 server error response
// 404 not found , 200 ok , 401 unauthorized do not allowed for you to enter here(not sent api key), 408 request time out
// 403 un unauthenticated (go login) ,500 internal server error (server crashed )
// 502 bad Gateway (500s server problems)

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container_main,
                NewsSourcesFragment()
            )
            .commit()

    }
}
// Retrofit
// open source libraries
// Retrofit since 2014 owner is square (make API call)
// Retrofit architecture is so simple
// square is owner also to dagger so difficult (learning curve is so bad) , dependency injection
// google made (hilt) is ( rubber on dagger)
// volley owner is google
// Retrofit can call Api and return it but cant make conversion (we work with objects in mobile) ->
// serialization (convert data to json) and deserialization (convert json to objects)
// many Adapters
// SOAP Software Oriented Architecture -> shghal XML
////////////////////////////////////////////////////////
// interceptor interrupt request and response to login ( logs http request and response data )
// not only for login ,caching(save data if do not have , if have internet get data from internet)

// Week9
// MVVM and MVC
// design patterns use in presentation layer(view)
// separation of concerns فصل الاهتمامات
// MVC (Model View Controller)
// VIEW -> XML , Fragments, Activity, RV, Adapters
// MODAL -> Data Classes , Apis, Firebase, Database
// CONTROLLER -> (Logic ,navigation,action) (Activity,Fragments)
// Controller comm with model and get data from model
// View comm with Controller and Controller comm with View
// View comm with model
// MVC not suitable for Mobile App Development
// .NetMVC web works with MVC


// MVVM (Model View ViewModel)
// VIEW -> XML , Fragments, Activity, RV, Adapters
// MODAL -> Data Classes , Apis, Firebase, Database
// ViewModel -> (Logic ,Data)

// ViewModel doesn't knew anything about View (no reference from view in ViewModel )
// Ant change in view doesn't effect ViewModel
// can use same viewModel with different Views
// separate logic and UI
// View comm with ViewModel
// ViewModel can comm with Model and get data from it
// ViewModel cant comm with View
// how to make comm bet ViewModel and View (notifyListener)
// observable (has data) comm with observer (Listener)(wait for data)
// observable design pattern
// observable has list of observers(listeners) when data change
// in java extends file observable or observer fun: notify,add
// We use liveData (observation) -> life cycle aware عارفه الي بيكلمها عايش ولا ميت
// activity or fragment is onStart or onDestroy
// Jetpack is calling مسمى for packages for android
// some of libraries and classes made for make android more easier