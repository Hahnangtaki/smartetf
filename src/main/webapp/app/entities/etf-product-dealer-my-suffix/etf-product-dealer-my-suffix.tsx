import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etf-product-dealer-my-suffix.reducer';
import { IEtfProductDealerMySuffix } from 'app/shared/model/etf-product-dealer-my-suffix.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfProductDealerMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EtfProductDealerMySuffix extends React.Component<IEtfProductDealerMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { etfProductDealerList, match } = this.props;
    return (
      <div>
        <h2 id="etf-product-dealer-my-suffix-heading">
          Etf Product Dealers
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Etf Product Dealer
          </Link>
        </h2>
        <div className="table-responsive">
          {etfProductDealerList && etfProductDealerList.length > 0 ? (
            <Table responsive>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Product Id</th>
                  <th>Dealer Id</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {etfProductDealerList.map((etfProductDealer, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${etfProductDealer.id}`} color="link" size="sm">
                        {etfProductDealer.id}
                      </Button>
                    </td>
                    <td>{etfProductDealer.productId}</td>
                    <td>{etfProductDealer.dealerId}</td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${etfProductDealer.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfProductDealer.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfProductDealer.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Etf Product Dealers found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ etfProductDealer }: IRootState) => ({
  etfProductDealerList: etfProductDealer.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfProductDealerMySuffix);
