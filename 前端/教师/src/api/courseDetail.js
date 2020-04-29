import request from '@/utils/request'

// index页数据
export function initial(courseId) {
  return request.get("/course_detail/initial", {
    params: {
      courseId
    }
  })
}

export function getChartData(courseId) {
  return request.get("/course_detail/chartData", {
    params: {
      courseId
    }
  })
}


//notice数据操作
export function getNotice(courseId) {
  return request.get("/notice", {
    params: {
      courseId
    }
  })
}

export function getNoticeById(noticeId) {
  return request.get("/notice/" + noticeId)
}

export function deleteNotice(noticeId) {
  return request.delete("/notice", {
    params: {
      noticeId
    }
  })
}

export function postNotice(data) {
  return request.post("/notice", data)
}

/**
 * 获取课程的详情信息
 */
export function getCourseInfo(courseId) {
  return request.get("/course_info/" + courseId)
}
