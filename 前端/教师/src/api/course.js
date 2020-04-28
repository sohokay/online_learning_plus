import request from '@/utils/request'

export function getCourse() {
  return request.get("/course")
}

export function postCourse(data) {
  return request.post('/course', data)
}

export function deleteCourse(courseId) {
  return request.delete('/course', {
    params: {
      courseId:courseId
    }
  })
}



