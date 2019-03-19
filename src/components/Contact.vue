<template>
    <div class="wrapper">
        <header-bar title="通讯录"/>

        <search-bar/>

        <div>
            <module-box>
                <module-item v-for="item in topContacts" :data="item"/>
            </module-box>
        </div>

        <tab-bar/>
    </div>
</template>
<style scoped>
    .wrapper{
        flex: 1;
        justify-content: flex-start;
        padding-top: 90px;
        padding-bottom: 101px;
        background-color: #fbf9fe;
    }
</style>
<script>
import HeaderBar from '@/components/common/HeaderBar';
import TabBar from '@/components/common/TabBar';
import SearchBar from '@/components/common/SearchBar';
import ModuleBox from '@/components/common/ModuleBox';
import ModuleItem from '@/components/common/ModuleItem';

export default {
  components: {
    HeaderBar,
    TabBar,
    SearchBar,
    ModuleBox,
    ModuleItem,
  },

  data() {
    return {
      topContacts: [
        { icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAA7VBMVEX6nTv////7tm76oUT++PH+8OH++vX7tGn92LH+7t3++/j+8ub+/Pn6nTz++fT95cv937/+/v77uXP+/fz81q396tb6oUP6q1f7sWT+/v36qVT8ypb81q7+8eT6rVv96dP92rX7uHH81az959D95s781av8z5/6qlX6o0f6pEr+79/94cT7vHn7u3j7vHr6oEL7u3f6nj77v4D7vXz8yJH++PL+7t76n0D7t3D96tX92bT93r37tWz+9u36nj37wob+9u76r1/96NH8xo7917D92LL++vb+8OL6rFr948j8zp78x4/+7+D81Kn6rVyUsFEiAAABZElEQVR4Xu3UVY7cUBSE4b+MzYzDzBNkZob9LycvltX2nXauTl4Syd8CSjrIf6JWq9W6wV6z12vuBV3+ys6uMrs72LHfUq61j1na0YpOilHYVkE7xOZEJSeYzCKVRLexuJLjGosDOQ6w6MvRx6IpRxOLCzkusJjKMcViIMcAi005NrHYkGMDk0OVHGKzWKpgucBoSwVbWIXbWrEdYpYqp2GKHZfODhndU+4+GYJGnCRxI8AfI+WeAADzI2WO5ng7Vm6StS1SLkrxdGus3PAOwDzSimiOj7vfhlox/g5kdUFWHR5GP1T0EwIVgxTwJ7++qmwEjXJQg2rnk7EcpxCXg+Lq5jx4KJdmkEgiJymhQvpINwqh5R/E4+OhbvYFPvuXdvZU64zgmX+zn2utCbzwH39Ha718BX3vhVSF13De9j0RVXhzBm97nkebqMKgC6fv/N7I+8qkDwDBx/hT9tj+PbVarfYb2XEVnzvRAPUAAAAASUVORK5CYII=', text: '新的朋友', },
        { icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAABRFBMVEUrokX////4/PlmvHleuHJOsWTz+fRiunX0+vX9/v213r6GypUuo0c+qlWb06c6qFJwwIJxwYPA48jw+PLt9++a0qbP6tXm9Okto0fK6NH+/v7Y7t1NsWPT7Nk4p1B+xo7a7t6u27jo9evc8OHD5Mqm17D7/fw3p08vo0j5/Pl5xIlWtGq54MHc7+BguXNrvn36/fvi8uaZ0qbs9u4sokav27hZtm2c06h6xYux3bvS69czpUxBq1hQsmW64cPL6NFYtm02p0/v+PGEyZMypUvJ59B7xYuy3bvZ7t5huXQ5qFKf1atRsmY8qVTr9u36/Po1pk6d1Knd8OHF5cxuv4BfuHKSz6Bct3BPsmVjunaOzZz8/fwwpErN6dO54MJ8xYx5xIqo2bOe1KrI5s5bt2/q9ezu9/D3+/jX7dtErVtduHGFyZSFauUDAAABrElEQVR4Ae3S03okURSG4e9v245tO2Pbtn3/51PP7nRUwa7OeOo9X174/gI+n28xutQbifQuRRc5knBFGyphjiAY0KZAkLY9GNM2YyHaNFLVDtUR2nNNu7wFEpHL5S940h3TLrFumJD0KYkXq3JZBe7J8RAPEnJJAN/keDaBvQG5DABX5NDnFNZicokBN2Tcx1pALgHguYw3WOuXSz+QljGHtbpc6sBpGWexlpFLBuiR8QJrYbmEgZcyytjr0i5dQPqWjMfYC2mXENAno4IXa9phDfjakPEEL7IL2mYhC2fmNs/nSU7b5IBLaprGo7o2XQBOqOkkXuW1KQ88VVMQr0I7T3ZRTefwKrPzq8+vy4iE8GZmVptmZ4DbkzICr/AgNd/QNo35FDB9Ssa799gKTmmXqSCO6LqMwnVsdA5KboOdOHJVyRHvS3OY7NCw9jQ8lAWO3YlLjmKeA9WSRe2rmKwBj27KeM0Bljt0oI5l4PhdyfGBfdXKcR0iXq45mT5KDvYzWpCFwij0HJxoRVZW4Kock+ynJCslDiNLPzzRzx9tvGSVZ5w/mM/n830HT58jXBOvQSEAAAAASUVORK5CYII=', text: '群聊', },
        { icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAAVFBMVEUngtf///+rzu+IuumTwOv5+/1boOD9/v45jNpeoeE4jNpAkNuCtudBkdvz+Pxgo+HY6PdfouFMl92qze/a6fh2sOVIld211PHu9fuUwevd6/iMvOlg5XIrAAAAsklEQVR4Xu3Otw7DQBADUe/dKQbn7P//TwPCNFahYqnCxU7H5oE7rSiKouhiPx3PTqfYoqvukO6Q7pDukOI8Du2UK4bg3OZdN0D+P8gJyOvYCagD8jrWAvVAXscmoAHI61gGGoG8jlX17Nz3QA6HmtT1w4hjbmfZVk7ayCl/5YSTZIeeskMv3aGy7sgSjizhyBKOLOHIEo4s4cgSjizhyBKOLOHIEo4u4ejl92cdiKIo+gLx3AX8g42WOQAAAABJRU5ErkJggg==', text: '标签', },
        { icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEgAAABICAMAAABiM0N1AAAA6lBMVEUngtcogtdfouGtz+/Z6fjz+PxKlt3O4/b///9jpOL4+/1Zn+D5+/0yiNnj7/mVwuswh9jv9vwvhthrqeNqqeOkyu7G3vTc6vjS5fa41vGLvOlCktzM4fVUnN/9/v6my+4xh9ji7vlan+C+2fNyreRlpuKjyu6iye1tquT8/f6Vwesqg9f+/v5NmN5kpeLd6/jg7flLl93C3POZxOw3i9qly+5Sm99Tm9+FuOjx9/zb6vidxuza6fgshdhXnd80idnR5Pbo8fq31fGrzu+sz++21PH1+f1Omd7k7/o4jNrK4PUrhNemzO6CtucNfNx8AAABWElEQVR4Ae3Bg3rkABiG0Xf81TbHrG27vf/LqZsnGWX6Z705h9BfIhQKRaKxeCIRj0UjBJJM6VMqSQBd3XJ0d2HW0yuX3h6M+vrl0d+HzYDqDGAyOKQ6Q8NYjKjBKBZjajCGxbgajGMRV4M4FhNqMIHFpBpMYjGlBlNYTKvBNBYzajCDyazqzGIzNy+P+TmMFuSxgNViWi7pRcwycskQQFaOLEHk5MgRRF6OPEEUJGliQpKKBFAqS1KlIknlElbVSk1vlpb0plapYrK8onersKp3K8t839q6Pm3Ahj6tr/E9m1tlfdmGHX0pb23Suerunhz9AP1y7O1W6VBmXy4HAAdyOczQiaNCTW7HAMdyqxWO8HVyKo8z3p3J4/QEP+fyuuDdpbyu8NMrr+sbgJtrefXiR/Vu7+DuVvXwowb3Dw/3aoCfhBo8PqpBAj9PCXUg8UQoFAr9oV4AkgMtYs7Eq2AAAAAASUVORK5CYII=', text: '公众号', },
      ]
    }
  },
  methods: {},
};
</script>
