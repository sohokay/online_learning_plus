import request from '@/utils/request'

export function getCourseTimeChart() {
  return request.get("/course_time_chart")
}

export function getSelectTimeChart() {
  return request.get("/select_time_chart")
}
