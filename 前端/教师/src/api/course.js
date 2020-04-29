import request from '@/utils/request'

/**
 * 获取当前教师的发布课程
 * @returns {AxiosPromise<any>}
 */
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



