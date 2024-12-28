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
      meta: { layout: "empty" },
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
          component: () => import("@/views/plan/PlanSelectSidoView.vue"),
        },
        {
          path: "list/:sidoCode",
          name: "plan-list-sido",
          component: () => import("@/views/plan/PlanListView.vue"),
          props: true,
        },
        {
          path: "info/:planId",
          name: "plan-info",
          component: () => import("@/views/plan/PlanInfoView.vue"),
        },
        {
          path: "make",
          name: "make-plan",
          component: () => import("@/views/plan/MakePlanView.vue"),
        },
        {
          path: "mission/make/:newPlanId",
          name: "make-mission",
          component: () => import("@/views/plan/mission/MakeMissionView.vue"),
        },
        {
          path: "mission/:newPlanId",
          name: "mission-upload",
          component: () => import("@/views/plan/MissionUploadView.vue"),
        },
      ],
    },
    {
      path: "/attraction",
      name: "attraction",
      component: () => import("@/views/attraction/AttractionView.vue"),
      redirect: "/attraction/list",
      children: [
        {
          path: "list",
          name: "attraction-list",
          component: () => import("@/views/attraction/AttractionListView.vue"),
        },
      ],
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
        {
          path: "status/:userId",
          name: "status-user",
          component: () => import("@/views/user/StatusView.vue"),
        },
        {
          path: "myPage",
          name: "myPage",
          component: () => import("@/views/user/MyPageView.vue"),
          children: [
            {
              path: "profile",
              name: "myPage-profile",
              component: () => import("@/views/user/myPage/EditProfile.vue"),
            },
            {
              path: "email",
              name: "myPage-email",
              component: () => import("@/views/user/myPage/EditEmail.vue"),
            },
            {
              path: "password",
              name: "myPage-password",
              component: () => import("@/views/user/myPage/EditPassword.vue"),
            },
          ],
        },
      ],
    },
  ],
});

export default router;
