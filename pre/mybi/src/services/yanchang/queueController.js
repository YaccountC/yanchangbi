// @ts-ignore
/* eslint-disable */
import { request } from '@umijs/max';
/** add GET /api/queue/add */
export async function addUsingGET(
// 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
params, options) {
    return request('/api/queue/add', {
        method: 'GET',
        params: {
            ...params,
        },
        ...(options || {}),
    });
}
/** get GET /api/queue/get */
export async function getUsingGET(options) {
    return request('/api/queue/get', {
        method: 'GET',
        ...(options || {}),
    });
}
//# sourceMappingURL=queueController.js.map