import { localAxios } from "@/util/http-common"

const local = localAxios()

async function login(param, success, fail) {
  await local.post(`/user/login`, param, { withCredentials: true }).then(success).catch(fail)
}

export { login }