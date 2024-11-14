import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "main",
      component: () => import("@/views/main/MainView.vue"),
    },
    {
      path: "/sign",
      name: "",
      redirect: "/sign/login",
      component: () => import("@/views/sign/SignView.vue"),
      children: [
        {
          path: "login",
          name: "login",
          component: () => import("@/views/sign/LoginView.vue"),
        },
        {
          path: "signup",
          name: "signup",
          component: () => import("@/views/sign/SignupView.vue"),
        },
      ],
    },
    {
      path: "/plan",
      name: "plan",
      redirect: "/plan/list",
      component: () => import("@/views/plan/PlanView.vue"),
      children: [
        {
          path: "list",
          name: "plan-list",
          component: () => import("@/views/plan/PlanListView.vue"),
        },
        {
          path: "make",
          name: "make-plan",
          component: () => import("@/views/plan/MakePlanView.vue"),
        },
      ],
    },
    {
      path: "/attraction",
      name: "attraction",
      component: () => import("@/views/attraction/AttractionView.vue"),
    },
    {
      path: "/user",
      name: "user",
      redirect: "/user/rank",
      component: () => import("@/views/user/UserView.vue"),
      children: [
        {
          path: "rank",
          name: "rank",
          component: () => import("@/views/user/RankView.vue"),
        },
        {
          path: "status",
          name: "status",
          component: () => import("@/views/user/StatusView.vue"),
        },
      ],
    },
  ],
});

export default router;
