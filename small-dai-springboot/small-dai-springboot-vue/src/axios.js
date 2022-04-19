import axios  from 'axios'
import qs from 'qs';
import router from './router'
import store from './store'

// 请求超时时间
axios.defaults.timeout = 10000;
axios.defaults.baseURL = "http://localhost";
axios.defaults.crossDomain = true;
axios.defaults.headers.common['token'] = '';

//前置拦截，在发起请求的时候的配置，在config中配置请求头等等的信息
axios.interceptors.request.use(config => {
	var token = localStorage.getItem("token");
	if(token !== null && token!== "undefined"){
		config.headers.token = token
	}else{
        config.headers.token=''
    }
	return config;
},
  error => {
    return Promise.error(error);
  });

  //后置拦截
axios.interceptors.response.use(response => {
    return response;
},
error => {
    return Promise.reject(error);
    }
);

/** 
 * get方法，对应get请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 */
 export function get(url, params){    
    return new Promise((resolve, reject) =>{        
        axios.get(url, {            
            params: params        
        })        
        .then(res => {     
            console.log(res)
            resolve(res);        
        })        
        .catch(err => {            
            reject(err)        
        })    
    });
}

/** 
 * post方法，对应post请求 
 * @param {String} url [请求的url地址] 
 * @param {Object} params [请求时携带的参数] 
 */
export function post(url, params= {}, json = false) {    
    // json格式请求头
    const headerJSON = {
        "Content-Type": "application/json"
    };
    // FormData格式请求头
    const headerFormData = {
        "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
    };
    return new Promise((resolve, reject) => {         
        axios
        .post(url, json ? JSON.stringify(params) : qs.stringify(params), {
          headers: json ? headerJSON : headerFormData
        })
        .then(res => {
          resolve(res);
        })
        .catch(err => {
          reject(err);
        });   
    });
}
/**
 *  put请求
 * @param {*} url 
 * @param {*} params 
 * @returns 
 */
export function put(url, params){
    return new Promise((resolve, reject) => { 
        axios.put(url,params).then(res => {
            resolve(res);
        }).catch(err=>{
            reject(err);
        })
    })
}