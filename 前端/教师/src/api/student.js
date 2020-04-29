import request from '@/utils/request'

export function getStudentList(courseId, current) {
  return request.get("/student", {
    params: {
      courseId,
      current
    }
  })
}

export function getByName(studentName, courseId) {
  return request.get("/student/" + studentName, {
    params: {
      courseId
    }
  })
}
