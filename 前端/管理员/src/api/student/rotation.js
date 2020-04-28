import request from '@/utils/request'


/**
 *
 * 获取轮播列表
 * @returns {AxiosPromise<any>}
 */
export function getRotation() {
  return request.get("/rotation")
}

export function postRotation(file) {
  var form = new FormData
  form.append("file", file)
  return request.post("/rotation", form)
}

export function putRotation(data) {
  return request.put("/rotation", data)
}
