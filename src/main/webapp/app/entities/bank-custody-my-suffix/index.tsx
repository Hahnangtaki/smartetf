import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import BankCustodyMySuffix from './bank-custody-my-suffix';
import BankCustodyMySuffixDetail from './bank-custody-my-suffix-detail';
import BankCustodyMySuffixUpdate from './bank-custody-my-suffix-update';
import BankCustodyMySuffixDeleteDialog from './bank-custody-my-suffix-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={BankCustodyMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={BankCustodyMySuffixUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={BankCustodyMySuffixDetail} />
      <ErrorBoundaryRoute path={match.url} component={BankCustodyMySuffix} />
    </Switch>
    <ErrorBoundaryRoute path={`${match.url}/:id/delete`} component={BankCustodyMySuffixDeleteDialog} />
  </>
);

export default Routes;
