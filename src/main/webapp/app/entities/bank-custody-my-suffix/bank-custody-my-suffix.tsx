import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './bank-custody-my-suffix.reducer';
import { IBankCustodyMySuffix } from 'app/shared/model/bank-custody-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBankCustodyMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class BankCustodyMySuffix extends React.Component<IBankCustodyMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { bankCustodyList, match } = this.props;
    return (
      <div>
        <h2 id="bank-custody-my-suffix-heading">
          Bank Custodies
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Bank Custody
          </Link>
        </h2>
        <div className="table-responsive">
          {bankCustodyList && bankCustodyList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Custody Id</th>
                  <th>Custody Code</th>
                  <th>Custodi Name</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {bankCustodyList.map((bankCustody, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${bankCustody.id}`} color="link" size="sm">
                        {bankCustody.id}
                      </Button>
                    </td>
                    <td>{bankCustody.custodyId}</td>
                    <td>{bankCustody.custodyCode}</td>
                    <td>{bankCustody.custodiName}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${bankCustody.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${bankCustody.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${bankCustody.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Bank Custodies found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ bankCustody }: IRootState) => ({
  bankCustodyList: bankCustody.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankCustodyMySuffix);
