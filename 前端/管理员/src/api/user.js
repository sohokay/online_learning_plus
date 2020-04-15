import request from '@/utils/request'

var url = "http://localhost:8000/v1"

/**
 *
 * 登录方法
 * @param data
 * @returns {AxiosPromise}
 */
export function login(data) {
  return request({
    url: url + '/login',
    method: 'post',
    data
  })
}

/**
 * 获取注册验证码
 * @param mobile
 * @returns {AxiosPromise}
 */
export function getRegisterCode(mobile) {
  return request({
    url: url + '/verification',
    method: 'get',
    params: {
      mobile: mobile
    }
  })
}

/**
 * 获取登录验证码
 * @param mobile
 * @returns {AxiosPromise}
 */
export function getLoginCode(mobile) {
  return request({
    url: url + '/login-verification',
    method: 'get',
    params: {
      mobile: mobile
    }
  })
}

/**
 * 注册方法
 * @param data
 * @returns {AxiosPromise}
 */
export function register(data) {
  return request({
    url: url + '/register',
    method: 'post',
    data
  })
}

/**
 * 获取用户信息
 * @returns {AxiosPromise}
 */
export function getInfo() {
  return request({
    url: url + '/user-info',
    method: 'get'
  })
}
export function phoneLogin(data) {
  return request({
    url: url + '/phone-login',
    method: 'post',
    data
  })
}

export function putInfo(data) {
  return request.put(url + '/user-info', data)
}


export function putHead(data) {
  return request.put(url + '/portrait',
    data
  )
}

export function getPasswordVerification() {
  return request.get(url + "/password-verification")
}

export function getMobileVerification(data) {
  return request.get(url + "/phone-verification",
    {
      params: {
        mobile: data
      }
    }
  )
}

export function putPassword(data) {
  return request.put(url + "/password", data)
}


export function putMobile(data) {
  return request.put(url + "/mobile", data)
}

export function logout() {
  return request({
    url: '/logout',
    method: 'get'
  })
}
