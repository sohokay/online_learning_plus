import request from '@/utils/request'

export function getDiscuss(courseId) {
  return request.get("/discuss/" + courseId)
}

export function deleteDiscuss(discussId) {
  return request.delete("/discuss/" + discussId)
}

export function postDiscuss(data) {
  return request.post("/discuss", data)
}

export function getDiscussByID(discussId) {
  return request.get("/discuss", {
    params: {
      discussId
    }
  })
}

export function postReply(data) {
  return request.post("/reply", data)
}
