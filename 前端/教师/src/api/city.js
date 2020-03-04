import request from '@/utils/request'

export function getCityList(params) {
  return request({
    url: '/api/cityList',
    method: 'get',
    params
  })
}
export function deleteCityList(data) {
  return request({
    url: '/api/cityList',
    method: 'delete',
    data:{
      cityList:data
    }
  })
}


export function getCity(params) {
  return request({
    url: '/api/city',
    method: 'get',
    params
  })
}export function postCity(data) {
  return request({
    url: '/api/city',
    method: 'post',
    data
  })
}
export function deleteCity(data) {
  return request({
    url: '/api/city',
    method: 'delete',
    data
  })
}
