import Cookies from 'js-cookie'


export function setUserName(name) {
  return Cookies.set('userName', name)
}

export function getUserName() {
  return Cookies.get('userName')
}
