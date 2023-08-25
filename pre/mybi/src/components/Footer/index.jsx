import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import { useIntl } from '@umijs/max';
import React from 'react';
const Footer = () => {
    const intl = useIntl();
    const defaultMessage = intl.formatMessage({
        id: 'app.copyright.produced',
        defaultMessage: 'Y智能AI',
    });
    const currentYear = new Date().getFullYear();
    return (<DefaultFooter style={{
            background: 'none',
        }} copyright={`${currentYear} ${defaultMessage}`} links={[
            {
                key: 'Y智能AI',
                title: 'Y智能AI',
                href: 'https://pro.ant.design',
                blankTarget: true,
            },
            {
                key: 'github',
                title: <GithubOutlined />,
                href: 'https://github.com/ant-design/ant-design-pro',
                blankTarget: true,
            },
            {
                key: 'Y智能AI',
                title: 'Y智能AI',
                href: 'https://ant.design',
                blankTarget: true,
            },
        ]}/>);
};
export default Footer;
//# sourceMappingURL=index.jsx.map