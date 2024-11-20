import axios from 'axios'
import { httpStatus } from '@/util/http-status.js'
import router from '@/router'

const { VITE_LOCAL_API_URL } = import.meta.env

export const instance = axios.create({
    baseURL: VITE_LOCAL_API_URL,
    withCredentials: true
})

// Request 발생 시 적용할 기본 속성 설정.

instance.defaults.headers.post['Content-Type'] = 'application/json'
instance.defaults.headers.put['Content-Type'] = 'application/json'

instance.interceptors.request.use( (config) => {
    return config
}, (error) => {
    return Promise.reject(error)
})

let isTokenRefreshing = false

instance.interceptors.response.use( (response) => {
    return response
}, async (error) => {

    // 에러가 발생했던 요청과 그 응답 객체를 저장.
    const { config, response } = error

    // 응답으로 Unauthorized가 발생한 경우.
    // -> 새로고침으로 토큰이 사라졌거나, 만료, 조작된 경우.
    // -> RefreshToken을 이용하여 재발급 진행.
    if(response.status === httpStatus.UNAUTHORIZED) {
        console.log("RefreshToken을 이용한 재발급 진행")
        // /auth/refresh로 요청을 진행해야 하므로, 기존의 요청을 저장.
        const prevRequest = config

        // 토큰 재발급이 이루어지는 중간에 새로운 요청이 들어오는 것을 방지.
        if(!isTokenRefreshing) {
            isTokenRefreshing = true

            return await instance.post('/user/refresh')
              .then( (response) => {
                  const newAccessToken = response.headers.authorization
                  
                  if(newAccessToken) {
                    console.log("새로운 Access Token 발급 성공")
                    instance.defaults.headers.common['Authorization'] = newAccessToken
                    prevRequest.headers.authorization = newAccessToken
  
                    return instance(prevRequest)
                  }
              })
              .catch( (error) => {
                console.log("Access Token 재발급 실패:", error)
                alert("로그인이 필요합니다.")
                router.push('/sign/login')
              })
              .finally( () => {
                isTokenRefreshing = false
              })
        }
    }
    else if(response.status === httpStatus.FORBIDDEN) {
        alert()
    }

    return Promise.reject(error)
})