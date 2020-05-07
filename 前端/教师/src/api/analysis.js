import request from '@/utils/request'

export function getData(courseId, studentId, actionId) {
  return request.get("/student_analysis/" + studentId, {
    params: {

      actionId, courseId
    }
  })
}

export function getStudentEvaluate(courseName) {
  return request.get("/student_evaluate", {
    params: {
      courseName
    }
  })
}

export function postEvaluate(studentEvaluate) {
  return request.post("/student_evaluate",
    studentEvaluate
  )
}
