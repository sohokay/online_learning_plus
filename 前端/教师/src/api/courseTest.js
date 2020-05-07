import request from '@/utils/request'


export function postTest(data) {
  return request.post("/test", data)
}

export function getTest(courseId) {
  return request.get("/test", {
    params: {
      courseId
    }
  })
}

export function getTestDetail(testId) {
  return request.get('/test_details', {
    params: {
      testId
    }
  })
}
