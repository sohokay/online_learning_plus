import Cookies from 'js-cookie'


export function setUserName(name) {
  return Cookies.set('userName', name)
}

export function getUserName() {
  return Cookies.get('userName')
}

/**
 * 设置头像
 * @param header
 * @returns {*}
 */
export function setHead(header) {
  return Cookies.set('header',header)
}

/**
 * 获取头像
 * @returns {*}
 */
export function getHead() {
  return Cookies.get('header')
}
