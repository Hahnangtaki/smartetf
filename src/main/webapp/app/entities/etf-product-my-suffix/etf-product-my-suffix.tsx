import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './etf-product-my-suffix.reducer';
import { IEtfProductMySuffix } from 'app/shared/model/etf-product-my-suffix.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IEtfProductMySuffixProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export class EtfProductMySuffix extends React.Component<IEtfProductMySuffixProps> {
  componentDidMount() {
    this.props.getEntities();
  }

  render() {
    const { etfProductList, match } = this.props;
    return (
      <div>
        <h2 id="etf-product-my-suffix-heading">
          Etf Products
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Etf Product
          </Link>
        </h2>
        <div className="table-responsive">
          {etfProductList && etfProductList.length > 0 ? (
            <Table responsive aria-describedby="etf-product-my-suffix-heading">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Product Code</th>
                  <th>Product Name</th>
                  <th>Fund Category</th>
                  <th>Inception Date</th>
                  <th>Clearing House</th>
                  <th>Exchage</th>
                  <th>Unit Creation</th>
                  <th>Unit Price Initial</th>
                  <th>Unit Price Curr</th>
                  <th>Lot Per Basket</th>
                  <th>Index Reff</th>
                  <th>Rating</th>
                  <th>Fee</th>
                  <th>Dealer Participant</th>
                  <th>Mi</th>
                  <th>Bank Custody</th>
                  <th />
                </tr>
              </thead>
              <tbody>
                {etfProductList.map((etfProduct, i) => (
                  <tr key={`entity-${i}`}>
                    <td>
                      <Button tag={Link} to={`${match.url}/${etfProduct.id}`} color="link" size="sm">
                        {etfProduct.id}
                      </Button>
                    </td>
                    <td>{etfProduct.productCode}</td>
                    <td>{etfProduct.productName}</td>
                    <td>{etfProduct.fundCategory}</td>
                    <td>
                      <TextFormat type="date" value={etfProduct.inceptionDate} format={APP_LOCAL_DATE_FORMAT} />
                    </td>
                    <td>{etfProduct.clearingHouse}</td>
                    <td>{etfProduct.exchage}</td>
                    <td>{etfProduct.unitCreation}</td>
                    <td>{etfProduct.unitPriceInitial}</td>
                    <td>{etfProduct.unitPriceCurr}</td>
                    <td>{etfProduct.lotPerBasket}</td>
                    <td>{etfProduct.indexReff}</td>
                    <td>{etfProduct.rating}</td>
                    <td>{etfProduct.fee}</td>
                    <td>
                      {etfProduct.dealerParticipants
                        ? etfProduct.dealerParticipants.map((val, j) => (
                            <span key={j}>
                              <Link to={`dealer-participant-my-suffix/${val.id}`}>{val.id}</Link>
                              {j === etfProduct.dealerParticipants.length - 1 ? '' : ', '}
                            </span>
                          ))
                        : null}
                    </td>
                    <td>{etfProduct.miId ? <Link to={`mi-my-suffix/${etfProduct.miId}`}>{etfProduct.miId}</Link> : ''}</td>
                    <td>
                      {etfProduct.bankCustodyId ? (
                        <Link to={`bank-custody-my-suffix/${etfProduct.bankCustodyId}`}>{etfProduct.bankCustodyId}</Link>
                      ) : (
                        ''
                      )}
                    </td>
                    <td className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${etfProduct.id}`} color="info" size="sm">
                          <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfProduct.id}/edit`} color="primary" size="sm">
                          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                        </Button>
                        <Button tag={Link} to={`${match.url}/${etfProduct.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                        </Button>
                      </div>
                    </td>
                  </tr>
                ))}
              </tbody>
            </Table>
          ) : (
            <div className="alert alert-warning">No Etf Products found</div>
          )}
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ etfProduct }: IRootState) => ({
  etfProductList: etfProduct.entities
});

const mapDispatchToProps = {
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(EtfProductMySuffix);
